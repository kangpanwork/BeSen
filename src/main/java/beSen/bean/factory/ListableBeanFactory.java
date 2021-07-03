package beSen.bean.factory;

import java.util.Map;

/**
 * 扩展的功能接口
 * 可以支持AOP处理(根据父类的接口类型判断实现类是否同一类型，然后调用子类的方法实现前置处理，后置处理)
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 获取type类型的子类
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> Map<String, T> getBeansOfType(Class<T> type);
}
