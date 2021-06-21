package beSen.juc;

import java.util.concurrent.TimeUnit;

/**
 * 信号量，用来控制同时访问特定资源的线程数量，通过协调各个线程，以保证合理的使用资源
 */
public interface Semaphore {

    /**
     * 获取一个令牌，在获取到令牌、或者被其他线程调用中断之前线程一直处于阻塞状态。
     */
    void acquire();

    /**
     * 获取一个令牌，在获取到令牌、或者被其他线程调用中断、或超时之前线程一直处于阻塞状态
     *
     * @param permits
     */
    void acquire(int permits);

    /**
     * 获取一个令牌，在获取到令牌之前线程一直处于阻塞状态（忽略中断）。
     */
    void acquireUninterruptibly();

    /**
     * 尝试获得令牌，返回获取令牌成功或失败，不阻塞线程。
     */
    void tryAcquire();

    /**
     * 尝试获得令牌，在超时时间内循环尝试获取，直到尝试获取成功或超时返回，不阻塞线程。
     *
     * @param timeout
     * @param unit
     */
    void tryAcquire(long timeout, TimeUnit unit);

}
