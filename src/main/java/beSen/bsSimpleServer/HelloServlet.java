package beSen.bsSimpleServer;

import java.io.IOException;

/**
 * 装饰者
 */
public class HelloServlet extends AbstractSimpleServlet{

    private Servlet servlet;

    public HelloServlet(Servlet servlet) {
        super(servlet);
        this.servlet = servlet;
    }

    public void service(SimpleRequest request, SimpleResponse response) {
            System.out.println("hello world...");
            servlet.service(request,response);
    }
}
