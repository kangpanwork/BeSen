package beSen.bsSimpleServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class BsActionHandler implements HttpHandler {

    private final Action action;

    public BsActionHandler(Action action) {
        this.action = action;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        action.doAction(new HttpServerRequest(httpExchange), new HttpServerResponse(httpExchange));
    }
}
