package beSen.bsSimpleServer;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class SimpleResponse {

    private OutputStream outputStream;

    public SimpleResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(128);
        String tailPad = "\r\n";
        stringBuilder.append("HTTP/1.1 200 ").append(tailPad)
                .append("Content-Type:text/html").append(tailPad)
                .append(tailPad)
                .append("<html><head><meta charset=\"UTF-8\"></head><body>")
                .append(content)
                .append("</body></html>");
        outputStream.write(stringBuilder.toString().getBytes(Charset.forName("UTF-8")));
    }

}
