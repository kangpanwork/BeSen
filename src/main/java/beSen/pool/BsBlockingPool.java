package beSen.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 实现思路：利用阻塞队列 LinkedBlockingQueue
 * @see java.util.concurrent.LinkedBlockingQueue
 * 初始化对象池，添加对象，利用 LinkedBlockingQueue add() 方法
 * 对象池取对象，利用 LinkedBlockingQueue poll() 和 take() 方法
 * 对象归还对象池，利用 LinkedBlockingQueue put() 方法
 *
 *
 * @param <T>
 */

public final class BsBlockingPool<T> extends AbstractPool<T> implements BlockingPool<T> {

    /**
     * 对象池大小
     */
    private int size;
    /**
     * 对象池容器
     */
    private BlockingQueue objects;
    /**
     * 验证器
     */
    private Validator validator;
    /**
     * 对象工厂
     */
    private ObjectFactory objectFactory;

//    private ExecutorService executor = new ThreadPoolExecutor(100, Integer.MAX_VALUE,
//            60L, TimeUnit.MILLISECONDS,
//            new LinkedBlockingQueue<Runnable>());

    private ExecutorService executor = Executors.newCachedThreadPool();

    private volatile boolean shutdownCalled;

    public BsBlockingPool(int size, Validator validator, ObjectFactory objectFactory) {
        super();
        this.objectFactory = objectFactory;
        this.size = size;
        this.validator = validator;
        objects = new LinkedBlockingQueue<T>(size); // 使用的阻塞队列
        initializeObjects();
        shutdownCalled = false;
    }

    @Override
    public void release(T t) {
        super.release(t);
    }

    @Override
    void handlerInvalidReturn(T t) {

    }

    @Override
    void returnToPool(T t) {
        if (validator.isValid(t)) {
            executor.submit(new ObjectReturner(objects, t));
        }
    }

    @Override
    boolean isValid(T t) {
        return validator.isValid(t);
    }

    @Override
    public T get(long timeOut, TimeUnit timeUnit) {
        if (!shutdownCalled) {
            T t = null;
            try {
                /**
                 * 取出队列头部元素并返回；如果队列是空的，
                 * 则等待一定时间；如果时间到了依然没有获取到值，返回null。
                 */
                t = (T) objects.poll(timeOut, timeUnit);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
            return t;
        }
        throw new IllegalStateException("Object poll is already shutdown");
    }

    @Override
    public T get() {
        if (!shutdownCalled) {
            T t = null;
            try {
                /**
                 * 从队列头部取出元素，并返回；如果阻塞队列为空，将阻塞等待
                 */
                t = (T) objects.take();
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
            return t;
        }
        throw new IllegalStateException("Object poll is already shutdown");
    }

    /**
     * 关闭对象池
     */
    @Override
    public void shutdown() {
        shutdownCalled = true;
        executor.shutdown();
        clearResources();
    }

    /**
     * 填充对象池
     */
    private void initializeObjects() {
        for (int i = 0; i < size; ++i) {
            /**
             * 队列中加入元素
             */
            objects.add(objectFactory.createNewObject());
        }
    }

    /**
     * 清空对象池
     */
    private void clearResources() {
        for (Object obj : objects) {
            validator.invalidate(obj);
        }
    }

    private class ObjectReturner implements Callable<T> {

        private BlockingQueue blockingQueue;

        private T t;

        public ObjectReturner(BlockingQueue blockingQueue, T t) {
            this.blockingQueue = blockingQueue;
            this.t = t;
        }

        /**
         * 最终将对象插入到队列里的任务作为一个异步的的任务提交给一个Executor来执行，以便让客户端线程能立即返回
         *
         * @return
         * @throws Exception
         */
        @Override
        public T call() throws Exception {
            while (true) {
                try {
                    /**
                     * 阻塞方式向队列中插入元素，如果队列没满，则成功插入；
                     * 如果队列满了，则插入线程阻塞，等待队列非满的通知
                     */
                    blockingQueue.put(t);
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                }
                break;
            }
            return null;
        }
    }
}
