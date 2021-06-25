package beSen.bsSimpleServer;


import java.io.IOException;
import java.io.OutputStream;

public class SimpleResponse {

    private OutputStream outputStream;

    public SimpleResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder
                .append("HTTP/1.1 200 ")
                .append("\r\n")
                .append("Content-Type:text/html")
                .append("\r\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html>");
        outputStream.write(stringBuilder.toString().getBytes());
    }

}
