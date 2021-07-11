package beSen.rpc.client;

import beSen.rpc.proto.Peer;
import beSen.rpc.transport.TransportClient;

import java.util.List;

/**
 * 表示选择哪个server去连接
 *
 * @author 康盼Java开发工程师
 */
public interface TransportSelector {

    /**
     * 初始化所有的server端点
     * @param peers 可以连接的server端点信息
     * @param count 每个server端点中，client与server建立多少个连接
     */
    void init(List<Peer> peers, int count);

    /**
     * 选择一个transport 和 server做交互
     *
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     *
     * @param client
     */
    void release(TransportClient client);

    /**
     * 关闭
     */
    void close();

}
