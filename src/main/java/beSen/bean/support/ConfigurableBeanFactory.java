package beSen.bean.support;

import beSen.bean.aop.BeanPostProcessor;
import beSen.bean.factory.BeanFactory;
import beSen.bean.singleton.SingletonBeanRegistry;

/**
 * 组合了1.获取bean,2获取单例容器实例及注册单例
 * 开放了新的功能，用于前置及后置处理
 * addBeanPostProcessor 添加这些实现此接口的子类
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
