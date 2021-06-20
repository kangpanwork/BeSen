package beSen.pool;

/**
 * 对象池
 * 核心思想：如果一个类被频繁请求使用，那么不必每次都生成一个实例，
 * 可以将这个类的一些实例保存到一个池中，待需要使用的时候直接从池里获取。
 * 这个池被称为对象池，在实现细节上，可以是一个数组，一个链表，或者任何集合。
 *
 * @param <T>
 */
public interface Pool<T> {

    /**
     * 对象池获取对象，如果对象可以重复使用就返回给客户端
     *
     * @return
     */
    T get();

    /**
     * 对象返还给对象池，进行重复使用
     * 首先检查对象是否能重复使用，如果是就返还给对象池，如果否就舍弃这个对象
     *
     * @param t
     */
    void release(T t);

    /**
     * 关闭池机制 释放对象 不会造成内存泄漏
     */
    void shutdown();

    /**
     * 检验对象
     *
     * @param <T>
     */
    interface Validator<T> {
        /**
         * 验证对象
         *
         * @param t
         * @return
         */
        boolean isValid(T t);

        /**
         * 将对象置为无效
         *
         * @param t
         */
        void invalidate(T t);
    }
}
