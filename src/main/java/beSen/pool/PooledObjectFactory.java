package beSen.pool;

public interface PooledObjectFactory<T> {
    /**
     * 创建一个实例对象
     *
     * @return
     */
    T makeObject();

    /**
     * 在对象从对象池中取出会激活这个对象
     *
     * @param obj
     */
    void activateObject(T obj);

    /**
     * 在对象返回对象池的时候会被调用
     *
     * @param obj
     */
    void passivateObject(T obj);

    /**
     * 判断对象是否可用
     *
     * @param obj
     * @return
     */
    boolean validateObject(T obj);

    /**
     * 对象从对象池中销毁
     *
     * @param obj
     */
    void destroyObject(T obj);
}
