package beSen.pool;

import java.util.concurrent.TimeUnit;

/**
 * 创建阻塞的对象池，当没有对象可以使用的话，让客户端先阻塞
 *
 *
 * @param <T>
 */
public interface BlockingPool<T> extends Pool<T> {

    T get();

    /**
     * 阻塞机制：让客户端一直阻塞直到有可用对象返回，设置一个超时时间，超时就返回 null
     * 类似 LinkedBlockingQueue
     *
     * @param timeOut
     * @param timeUnit
     * @return
     */
    T get(long timeOut, TimeUnit timeUnit);
}
