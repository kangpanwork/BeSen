package beSen.rpc.example;


import beSen.rpc.transport.RequestHandler;

import com.alibaba.fastjson.JSON;
import org.eclipse.jetty.server.Server;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
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
        String configPort = "8080";
        String port = System.getProperty("server.http.port", configPort);
        System.out.println("Server started on port: " + port);
        Server server = new Server(8080);
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
            Class clazz = Class.forName(className);
            Optional op = Arrays.stream(clazz.getDeclaredMethods()).filter(method ->
                method.getName().equals(rest.getMethod())
            ).findFirst();
            if (op.isPresent()) {
                Object target = ServiceEnum.getTarget(className);
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
