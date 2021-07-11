package beSen;

import beSen.bsSimpleServer.Tomcat;
import beSen.rpc.client.RpcClient;
import beSen.rpc.example.CalcService;
import beSen.rpc.example.CalcServiceImpl;
import beSen.rpc.server.RpcServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CommandLineRunner
 *
 * @author 康盼Java开发工程师
 */
@SpringBootApplication
public class BeSenApplication implements ApplicationRunner {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BeSenApplication.class, args);
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Tomcat tomcat = new Tomcat(9876);
//        tomcat.start();
        RpcClient client = new RpcClient();
        CalcService proxy = client.getProxy(CalcService.class);
        int add = proxy.add(1, 2);
        int minus = proxy.minus(1, 2);
        System.out.println(add + "||" + minus);
    }
}
