package beSen.rpc.transport;

import beSen.rpc.proto.Peer;

import java.io.InputStream;

/**
 * 客户端
 *
 * @author 康盼Java开发工程师
 */
public interface TransportClient {

    /**
     * 创建连接
     *
     * @param peer
     */
    void connect(Peer peer);

    /**
     * 发送数据等待响应
     *
     * @param inputStream
     * @throws Exception
     * @return
     */
    InputStream write(InputStream inputStream) throws Exception;

    /**
     * 关闭连接
     */
    void close();
}
