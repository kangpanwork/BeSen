package beSen.test.bsSimpleServer;


import beSen.bsSimpleServer.AbstractSimpleServlet;
import beSen.bsSimpleServer.Servlet;
import beSen.bsSimpleServer.SimpleRequest;
import beSen.bsSimpleServer.SimpleResponse;

/**
 * 装饰者
 * @author 康盼Java开发工程师
 */
public class HelloServlet extends AbstractSimpleServlet {

    private Servlet servlet;

    public HelloServlet(Servlet servlet) {
        super(servlet);
        this.servlet = servlet;
    }

    @Override
    public void service(SimpleRequest request, SimpleResponse response) {
            System.out.println("hello world...");
            servlet.service(request,response);
    }
}
