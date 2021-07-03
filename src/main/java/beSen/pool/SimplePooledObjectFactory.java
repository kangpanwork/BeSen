package beSen.pool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟，真实使用请实现 PooledObjectFactory
 * @author 康盼Java开发工程师
 * @see org.apache.commons.pool2.PooledObjectFactory
 * 然后通过 ObjectPool pool = new GenericObjectPool(实现的类);
 * @see org.apache.commons.pool2.impl.GenericObjectPool
 */
public class SimplePooledObjectFactory implements PooledObjectFactory {

    private static AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Object makeObject() {
        counter.getAndIncrement();
        return null;
    }

    @Override
    public void activateObject(Object obj) {

    }

    @Override
    public void passivateObject(Object obj) {

    }

    @Override
    public boolean validateObject(Object obj) {
        return false;
    }

    @Override
    public void destroyObject(Object obj) {

    }
}
