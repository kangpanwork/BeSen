package beSen.pool;

import java.util.concurrent.TimeUnit;

public final class BsBlockingPool extends AbstractPool implements BlockingPool {

    @Override
    public void release(Object o) {
        super.release(o);
    }

    @Override
    void handlerInvalidReturn(Object o) {

    }

    @Override
    void returnToPool(Object o) {

    }

    @Override
    boolean isValid(Object o) {
        return false;
    }

    @Override
    public Object get(long time, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public void shutdown() {

    }
}
