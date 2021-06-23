package beSen.bsSimpleServer;

public class HelloServlet implements Servlet{

    private SimpleServlet simpleServlet;

    public HelloServlet() {
        simpleServlet = new SimpleServlet();
    }

    public void service(SimpleRequest request, SimpleResponse response) {
        simpleServlet.service(request,response);
    }
}
