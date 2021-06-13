package beSen.bsSimpleServer;



import java.io.IOException;

@FunctionalInterface
public interface Action {
    void doAction(HttpServerRequest httpServerRequest, HttpServerResponse httpServerResponse) throws IOException;
}
