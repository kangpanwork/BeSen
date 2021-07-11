package beSen.rpc.proto;

/**
 * 表示网络传输的一个端点
 *
 * @author 康盼Java开发工程师
 */
public class Peer {

    /**
     * 主机名或者IP
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    public Peer() {
    }

    public Peer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Peer{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
