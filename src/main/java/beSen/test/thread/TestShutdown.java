package beSen.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 康盼Java开发工程师
 */
public class TestShutdown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            String str = i + "";
            service.execute(() -> {
                System.out.println(str);
            });
        }
        service.shutdown();
        try {
            boolean isTermination =service.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println(isTermination);
            // 调用shutdown()方法后继续添加任务
            // service.execute(() -> System.out.println("ok"));
        } catch (InterruptedException e) {
            service.shutdownNow();
        }
        System.out.println("线程池已关闭");
    }
}
