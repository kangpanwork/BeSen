package beSen.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author 康盼Java开发工程师
 * @FunctionalInterface
 */
public interface RequestHandler {

    /**
     * inputStream -> outputStream
     *
     * @param inputStream
     * @param outputStream
     */
    void onRequest(InputStream inputStream, OutputStream outputStream);
}
