package beSen.bsSimpleServer.HttpClient;

/**
 * 请求模型
 */
public class RequestEntity {
    /**
     * 请求URL
     */
    private final String url;
    /**
     * 请求token
     */
    private final String token;
    /**
     * 请求方法
     */
    private final String method;
    /**
     * 请求参数
     */
    private final String parameter;
    /**
     * 响应结果
     */
    private String responseResult;

    /**
     * 响应状态
     */
    private int statusCode;

    /**
     * 响应内容长度
     */
    private long contentLength;

    public RequestEntity(String url, String token, String method, String parameter) {
        this.url = url;
        this.token = token;
        this.method = method;
        this.parameter = parameter;
    }


    public String getResponseResult() {
        return responseResult;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public String getMethod() {
        return method;
    }

    public String getParameter() {
        return parameter;
    }

    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }
}
