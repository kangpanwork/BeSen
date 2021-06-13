package beSen.bsSimpleServer;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Oracle JDK提供了一个简单的Http服务端类，叫做HttpServer，当然它是sun的私有包，位于com.sun.net.httpserver
 * 用于在不引入Tomcat、Jetty等容器的情况下，实现简单的Http请求处理
 */
public class BsSimpleServer {

    private final HttpServer httpServer;


    public BsSimpleServer(int port) throws IOException {
        this(new InetSocketAddress(port));
    }

    public BsSimpleServer(InetSocketAddress address) throws IOException {
       httpServer = HttpServer.create(address, 0);
    }

    /**
     * 启动一个Http服务
     */
    public void start() {
        httpServer.start();
    }

    /**
     * BsSimpleServer bsSimpleServer = new BsSimpleServer(8989);
     * bsSimpleServer.addAction("/",((httpServerRequest, httpServerResponse) -> httpServerResponse.write("hello world"))).start();
     * 通过调用addAction方法，定义不同path的处理规则，实现相应的功能
     * 绑定在"/"路径下，此时我们可以访问，输出 hello world
     *
     * @param path
     * @param action
     * @return
     */
    public BsSimpleServer addAction(String path, Action action) {
        HttpHandler httpHandler = new BsActionHandler(action);
        httpServer.createContext(path,httpHandler);
        return this;
    }

}
