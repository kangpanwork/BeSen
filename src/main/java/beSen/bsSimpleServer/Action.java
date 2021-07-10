package beSen.bsSimpleServer;



import java.io.IOException;

@FunctionalInterface
public interface Action {
    /**
     * request -> response
     *
     * @param httpServerRequest
     * @param httpServerResponse
     * @throws IOException
     */
    void doAction(HttpServerRequest httpServerRequest, HttpServerResponse httpServerResponse) throws IOException;
}
