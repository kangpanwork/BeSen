package beSen.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class BsBlockingPool<T> extends AbstractPool<T> implements BlockingPool<T> {

    private int size;
    private BlockingQueue objects;
    private Validator validator;
    private ObjectFactory objectFactory;
//    private ExecutorService executor = new ThreadPoolExecutor(100, Integer.MAX_VALUE,
//            60L, TimeUnit.MILLISECONDS,
//            new LinkedBlockingQueue<Runnable>());

    private ExecutorService executor = Executors.newCachedThreadPool();

    private volatile  boolean shutdownCalled;

    public BsBlockingPool(int size, Validator validator, ObjectFactory objectFactory) {
        super();
        this.objectFactory = objectFactory;
        this.size = size;
        this.validator =validator;
        objects = new LinkedBlockingQueue<T>(size);
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
            executor.submit(new ObjectReturner(objects,t));
        }
    }

    @Override
    boolean isValid(T t) {
        return false;
    }

    @Override
    public T get(long timeOut, TimeUnit timeUnit) {
        if (!shutdownCalled) {
            T t = null;
            try {
                t = (T) objects.poll(timeOut,timeUnit);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
            return t;
        }
        throw new IllegalStateException("Object poll is already shutdown");
    }

    @Override
    public T get() {
        return null;
    }

    @Override
    public void shutdown() {
        shutdownCalled= true;
        executor.shutdown();
        clearResources();
    }
    private void initializeObjects() {
        for (int i = 0; i < size; ++i) {
            objects.add(objectFactory.createNewObject());
        }
    }

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
