package beSen.pool;

public abstract class AbstractPool<T> implements Pool<T> {

    /**
     * 对象返还给对象池
     *
     * @param t
     */
    @Override
    public void release(T t) {
        if (isValid(t)) {
            returnToPool(t);
        } else {
            handlerInvalidReturn(t);
        }
    }

    /**
     * 对象池必须得先验证对象后才能把它放回到池里
     * 无效的话应该怎么处理（handlerInvalidReturn）
     * @param t
     */
    abstract void handlerInvalidReturn(T t);

    /**
     * 怎么把一个有效的对象放回到池里（returnToPool）
     *
     * @param t
     */
    abstract void returnToPool(T t);

    /**
     * 验证对象
     *
     * @param t
     * @return
     */
    abstract boolean isValid(T t);
}
