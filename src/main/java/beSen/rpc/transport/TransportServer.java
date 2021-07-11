package beSen.rpc.transport;

/**
 * 网络配置信息
 *
 * @author 康盼Java开发工程师
 */
public interface TransportServer {

    /**
     * 监听端口
     *
     * @param port
     * @param requestHandler
     * @throws Exception
     */
    void init(int port,RequestHandler requestHandler) throws Exception;

    /**
     * 启动，接收请求
     *
     * @throws Exception
     */
    void start() throws Exception;

    /**
     * 关闭监听
     *
     * @throws Exception
     */
    void stop() throws Exception;
}
