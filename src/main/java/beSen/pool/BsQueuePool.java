package beSen.pool;

public class BsQueuePool<T> extends AbstractPool<T> {
    @Override
    public T get() {
        return null;
    }

    @Override
    public void release(T t) {
        super.release(t);
    }

    @Override
    public void shutdown() {

    }

    @Override
    void handlerInvalidReturn(T t) {

    }

    @Override
    void returnToPool(T t) {

    }

    @Override
    boolean isValid(T t) {
        return false;
    }
}
