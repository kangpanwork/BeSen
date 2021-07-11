package beSen.rpc.client;

import beSen.reflect.ReflectUtils;
import beSen.rpc.proto.Peer;
import beSen.rpc.transport.HttpTransportClient;
import beSen.rpc.transport.TransportClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 表示选择哪个server去连接
 *
 * @author 康盼Java开发工程师
 */
public class RandomTransportSelector implements TransportSelector {

    /**
     * 存储所有连接好的client
     */
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    @Override
    public void init(List<Peer> peers, int count) {
        //count取大于等于1的
        count = Math.max(count, 1);
        for (Peer peer : peers) {
            for (int i = 0; i < count; ++i) {
                TransportClient transportClient = new HttpTransportClient();
                transportClient.connect(peer);
                clients.add(transportClient);
            }
        }
    }

    @Override
    public TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public void close() {
        clients.forEach(TransportClient::close);
        clients.clear();
    }
}
