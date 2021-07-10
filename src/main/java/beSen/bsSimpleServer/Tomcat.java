package beSen.bsSimpleServer;

import beSen.aop.CglibAopProxy;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 康盼Java开发工程师
 */
public class Tomcat {

    private int port;

    private boolean b;

    public Tomcat(int port) {
        this.port = port;
        this.b = true;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server on port:" + port + ",tomcat is running...");
            while (b) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                doService(new SimpleRequest(inputStream), new SimpleResponse(outputStream));
                socket.close();
            }
        } catch (IOException e) {
        }
    }

    /**
     * http://localhost:8080/hello -> HelloServlet
     *
     * @param request
     * @param response
     */
    private void doService(SimpleRequest request, SimpleResponse response) {
        String packageName = this.getClass().getPackage().getName();
        String className = request.getUrl().substring(1);
        System.out.println("className:" + className);
        try {
            // 这里路由到beSen.test.controller的类
            // 有点像注册中心，另外启动一个端口的服务器，然后监听浏览器URL，通过解析获取其它端口的代理类，再调它的方法
            final String baseUrl = "beSen.test.controller.";
            if (className.indexOf("/") != -1) {
                String clazzName = baseUrl + StrUtil.upperFirst(className.split("/")[0]);
                String methodName = className.split("/")[1];
                Class cls = Class.forName(clazzName);
                // {AttachmentController$$EnhancerByCGLIB$$156a045@7202}
                Object obj = new CglibAopProxy(null).getProxy(cls,null,null);
                Method method = cls.getMethod(methodName);
                method.invoke(obj);
            } else {
                String servletName = packageName + "." + className.substring(0, 1).toUpperCase()
                        + className.substring(1) + "Servlet";
                System.out.println("servletName or serviceName:" + className);

                Class cls = Class.forName(servletName);
                SimpleServlet simpleServlet = new SimpleServlet();
                Class[] args = new Class[1];
                args[0] = Servlet.class;
                Servlet servlet = (Servlet) cls.getDeclaredConstructor(args).newInstance(simpleServlet);
                servlet.service(request, response);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
