package beSen.bsSimpleServer;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;


/**
 * @author 康盼Java开发工程师
 */
public class BsHttpServerBase {

//    此类封装接收的HTTP请求和在一个交换中生成的响应。 它提供了检查来自客户端的请求以及构建和发送响应的方法。
//    HttpExchange的典型生命周期如下所示。
//
//    getRequestMethod()确定命令
//    getRequestHeaders()检查请求标头（如果需要）
//    getRequestBody()返回InputStream以读取请求正文。 读取请求主体后，流已关闭。
//    getResponseHeaders()设置除content-length之外的任何响应头
//    sendResponseHeaders(int,long)发送响应头。 必须在下一步之前调用。
//    getResponseBody()获取OutputStream发送响应正文。 写入响应主体后，必须关闭流以终止交换。

    private final HttpExchange httpExchange;

    public BsHttpServerBase(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    public HttpContext getHttpContext() {
       return httpExchange.getHttpContext();
    }

    public HttpExchange getHttpExchange() {
        return httpExchange;
    }
}
