package beSen.bsSimpleServer;

import java.io.IOException;
import java.io.InputStream;

public class SimpleRequest {

    private String url;
    private String method;

    public SimpleRequest(InputStream inputStream) throws IOException {
        byte[] b = new byte[64];
        int len = inputStream.read(b);
        if (len > 0) {
            String s = new String(b, 0, len);
            String[] arr = s.split("\\s");// 只要出现空白就匹配
            this.method = arr[0];
            this.url = arr[1];
        }
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
