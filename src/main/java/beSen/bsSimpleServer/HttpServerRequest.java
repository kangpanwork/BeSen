package beSen.bsSimpleServer;


import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;

public class HttpServerRequest extends BsHttpServerBase {

    public HttpServerRequest(HttpExchange httpExchange) {
        super(httpExchange);
    }

    @Override
    public HttpContext getHttpContext() {
        return super.getHttpContext();
    }
}
