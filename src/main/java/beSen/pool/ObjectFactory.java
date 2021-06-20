package beSen.pool;

/**
 * 创建对象的工厂，不依赖对象池来创建新对象。
 * 创建的对象用来填充对象池。
 *
 * @param <T>
 */
public interface ObjectFactory<T> {

    /**
     * 创建新的对象
     *
     * @return
     */
    T createNewObject();
}
