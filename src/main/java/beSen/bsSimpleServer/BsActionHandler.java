package beSen.bsSimpleServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

//class MyHandler implements HttpHandler {
//    public void handle(HttpExchange t) throws IOException {
//        InputStream is = t.getRequestBody();
//        read(is); // .. read the request body
//        String response = "This is the response";
//        t.sendResponseHeaders(200, response.length());
//        OutputStream os = t.getResponseBody();
//        os.write(response.getBytes());
//        os.close();
//    }
//}
//   ...
//
//           HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
//           server.createContext("/applications/myapp", new MyHandler());
//           server.setExecutor(null); // creates a default executor
//           server.start();

/**
 * https://www.apiref.com/java11-zh/jdk.httpserver/com/sun/net/httpserver/package-summary.html
 */
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
