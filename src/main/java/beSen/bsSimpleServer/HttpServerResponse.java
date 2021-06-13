package beSen.bsSimpleServer;


import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpServerResponse extends BsHttpServerBase {
    public HttpServerResponse(HttpExchange httpExchange) {
        super(httpExchange);
    }

    @Override
    public HttpContext getHttpContext() {
        return super.getHttpContext();
    }

    public HttpServerResponse write(File file) throws Exception {
        long fileSize = file.length();
        if (fileSize > 2147483647L) {
            throw new IllegalArgumentException("File size is too bigger than 2147483647");
        } else {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream in = new BufferedInputStream(fileInputStream);
            HttpExchange httpExchange = getHttpExchange();
            OutputStream out = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(200,fileSize);
            byte[] buffer = new byte[8192];
            int readSize;
            while((readSize = in.read(buffer)) != -1) {
                out.write(buffer, 0, readSize);
            }
            out.flush();
            out.close();
            in.close();
            return this;
        }
    }

    public HttpServerResponse write(String data) throws Exception {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        HttpExchange httpExchange = getHttpExchange();
        OutputStream out = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(200,data.length());
        byte[] buffer = new byte[8192];
        int readSize;
        while((readSize = in.read(buffer)) != -1) {
            out.write(buffer, 0, readSize);
        }
        out.flush();
        out.close();
        in.close();
        return this;
    }
}
