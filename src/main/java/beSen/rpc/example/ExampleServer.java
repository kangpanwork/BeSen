package beSen.rpc.example;


import beSen.rpc.transport.HttpTransportServer;
import beSen.rpc.transport.RequestHandler;

import org.eclipse.jetty.server.Server;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


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
        super.doGet(req, resp);
        System.out.println("-----");
    }

    private RequestHandler requestHandler = new RequestHandler() {
        @Override
        public void onRequest(InputStream inputStream, OutputStream outputStream) {

        }
    };
}
