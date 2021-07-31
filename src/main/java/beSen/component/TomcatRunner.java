package beSen.component;

import beSen.bsSimpleServer.Tomcat;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author 康盼Java开发工程师
 */
public class TomcatRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Tomcat tomcat = new Tomcat(9876);
        tomcat.start();
    }
}
