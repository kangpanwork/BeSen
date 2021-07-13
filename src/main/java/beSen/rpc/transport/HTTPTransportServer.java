package beSen.rpc.transport;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 网络配置信息
 *
 * @author 康盼Java开发工程师
 */
public class HttpTransportServer implements TransportServer {

    private RequestHandler requestHandler;
    private Server server;

    @Override
    public void init(int port, RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.server = new Server(port);
        // servlet 接收请求，拦截 /* 路径，将输入流转换输出流
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        ServletHolder servletHolder = new ServletHolder(new RequestServlet());
        servletContextHandler.addServlet(servletHolder,"/*");
        server.setHandler(servletContextHandler);

    }

    @Override
    public void start() throws Exception {
        server.start();
        server.join();
    }

    @Override
    public void stop() throws Exception {
        server.stop();
    }

    private class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            System.out.println("client connect!!!");
            InputStream inputStream = req.getInputStream();
            OutputStream outputStream = resp.getOutputStream();
            if (requestHandler != null) {
                requestHandler.onRequest(inputStream, outputStream);
            }
            outputStream.flush();
        }
    }
}
