package beSen.bsSimpleServer;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;


public class BsHttpServerBase {

    private final HttpExchange httpExchange;

    public BsHttpServerBase(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    public HttpContext getHttpContext() {
       return httpExchange.getHttpContext();
    }

    public HttpExchange getHttpExchange() {
        return httpExchange;
    }
}
