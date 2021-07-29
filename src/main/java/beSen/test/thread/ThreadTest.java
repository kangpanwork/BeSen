package beSen.test.thread;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 康盼Java开发工程师
 *
 */
public class ThreadTest {

    /**
     * 当前线程数 <= maximumPoolSize + LinkedBlockingQueue的容量，否则执行 RejectHandler
     */
    @Test
    public void test() {
        AtomicInteger atomicInteger = new AtomicInteger();
        Executor executor =  new ThreadPoolExecutor(10, 10,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                r -> {
                    final Thread thread = Executors.defaultThreadFactory().newThread(r);
                    thread.setDaemon(true);
                    thread.setName("解析文件的线程名：" +  atomicInteger.incrementAndGet());
                    return thread;
                },new RejectHandler());


        List<CompletableFuture<Void>> futures =
                IntStream.range(0, 10).boxed()
                        .map(i -> CompletableFuture.runAsync(() -> execute(i), executor))
                        .collect(Collectors.toList());

        futures.forEach(CompletableFuture::join);

    }

    /**
     * 执行解析文件
     *
     * @param i 参数判断来解析哪个文件夹
     */
    private void execute(Integer i) {
        // 举个例子 当i=1 那么当前线程就是解析第一个文件夹
        if(i == 1) {
            System.out.println("解析第一个文件夹");
        }
        System.out.println(Thread.currentThread().getName() + "执行");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }




    /**
     * 自定义拒绝策略
     *  {@link ThreadPoolExecutor.DiscardPolicy}
     */
    private class RejectHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("不做处理");
        }

    }


}
