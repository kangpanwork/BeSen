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
    public static void main(String[] args) {
        SpringApplication.run(BeSenApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Tomcat tomcat = new Tomcat(9876);
        tomcat.start();
    }
}
