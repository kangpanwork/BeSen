package beSen.bsSimpleServer;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 康盼Java开发工程师
 */
public class SimpleRequest {

    private String url;
    private String method;

    /**
     * 当tomcat启动 输入对应的端口 这里会解析url
     * request url:POST /attach/batchInsert HTTP/1.1
     *
     * @param inputStream
     * @throws IOException
     */
    public SimpleRequest(InputStream inputStream) throws IOException {
        byte[] b = new byte[64];
        int len = inputStream.read(b);
        if (len > 0) {
            String s = new String(b, 0, len);
            System.out.println("request url:" + s);
            // 只要出现空白就匹配
            String[] arr = s.split("\\s");
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
