package beSen.rpc.example;

import beSen.rpc.server.RpcServer;
/**
 * @author 康盼Java开发工程师
 */
public class Server {
    public static void main(String[] args) throws Exception {
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
