package beSen.rpc.example;


import beSen.rpc.spring.bean.ProviderBean;
import beSen.rpc.spring.bean.ServerBean;
import beSen.rpc.transport.RequestHandler;

import com.alibaba.fastjson.JSON;
import org.eclipse.jetty.server.Server;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.IOUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;


/**
 * @author 康盼Java开发工程师
 */
public class ExampleServer {
    public static void main(String[] args) throws Exception {
        String[] configs = {"server.xml"};
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(configs);
        ServerBean s = (ServerBean) beanFactory.getBean("server");
        Server server = new Server(s.getPort());
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        server.setHandler(servletContextHandler);

        ServletHolder servletHolder = new ServletHolder(new RequestServlet());
        servletContextHandler.addServlet(servletHolder, "/*");
        server.start();
        server.join();
    }


}


class RequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("client connect!!!");
        InputStream inputStream = req.getInputStream();
        OutputStream outputStream = resp.getOutputStream();
        requestHandler.onRequest(inputStream, outputStream);
        outputStream.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("client connect!!!");
        InputStream inputStream = req.getInputStream();
        OutputStream outputStream = resp.getOutputStream();
        requestHandler.onRequest(inputStream, outputStream);
        outputStream.flush();
    }

    /**
     * 处理httpURLConnection.getOutputStream()的输出流对应HttpServletRequest的输入流
     */
    private RequestHandler requestHandler = (inputStream,outputStream) -> {
        try {
            byte[] bytes = IOUtils.readFully(inputStream, inputStream.available(), true);
            RestServiceInfo rest = JSON.parseObject(bytes,RestServiceInfo.class);
            String className = rest.getClazz();
            String[] configs = {"provider.xml"};
            BeanFactory beanFactory = new ClassPathXmlApplicationContext(configs);
            ProviderBean p = (ProviderBean) beanFactory.getBean("provider");
            Class clazz = Class.forName(className);
            Optional op = Arrays.stream(clazz.getDeclaredMethods()).filter(method ->
                method.getName().equals(rest.getMethod())
            ).findFirst();
            if (op.isPresent()) {
                // 从枚举中获取 Object target = ServiceEnum.getTarget(className);
                Object target = Class.forName(p.getRef()).newInstance();
                Method method = (Method)op.get();
                Object result = method.invoke(target,rest.getArgs());
                byte[] message = JSON.toJSONBytes(result);
                outputStream.write(message);
            } else {
                outputStream.write(String.valueOf(0).getBytes());
            }

        } catch (Exception e) {
        }
    };
}
