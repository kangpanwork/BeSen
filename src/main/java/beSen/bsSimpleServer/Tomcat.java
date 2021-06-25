package beSen.bsSimpleServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

    private void doService(SimpleRequest request, SimpleResponse response) {
        String packageName = this.getClass().getPackage().getName();
        String className = request.getUrl().substring(1);
        String servletName = packageName + "." + className.substring(0, 1).toUpperCase()
                + className.substring(1) + "Servlet";
        try {
            Class cls = Class.forName(servletName);
            SimpleServlet simpleServlet = new SimpleServlet();
            Class[] args = new Class[1];
            args[0] = Servlet.class;
            Servlet servlet = (Servlet) cls.getDeclaredConstructor(args).newInstance(simpleServlet);
            servlet.service(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat(8080);
        tomcat.start();
    }
}
