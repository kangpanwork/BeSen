package beSen.config;

import beSen.component.TomcatRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
@Configuration
public class AfterRun {
    @Autowired
    Environment environment;


    @EventListener({ApplicationReadyEvent.class})
    @Order(99)
    public void test() throws Exception {
        System.out.println("记录日志后 测试 Order");
        String port = environment.getProperty("local.server.port");
        String url = "http://localhost:" + port;
        List<Object> runners = new ArrayList<>();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TomcatRunner.class);
        applicationContext.refresh();
        runners.addAll(applicationContext.getBeansOfType(ApplicationRunner.class).values());
        runners.addAll(applicationContext.getBeansOfType(CommandLineRunner.class).values());
        AnnotationAwareOrderComparator.sort(runners);
        for (Object runner : new LinkedHashSet(runners)) {
            if (runner instanceof ApplicationRunner) {
                callRunner((ApplicationRunner)runner);
            }
            if (runner instanceof CommandLineRunner) {
                callRunner((CommandLineRunner)runner);
            }
        }
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);

    }

    private void callRunner(ApplicationRunner runner) throws Exception {
        runner.run(null);
    }
    private void callRunner(CommandLineRunner runner) throws Exception {
        runner.run(null);
    }

}
