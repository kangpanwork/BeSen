package beSen;

import beSen.bsSimpleServer.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeSenApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeSenApplication.class, args);
        Tomcat tomcat = new Tomcat(9876);
        tomcat.start();
    }
}
