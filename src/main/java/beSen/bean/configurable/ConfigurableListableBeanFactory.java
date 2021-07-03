package beSen.bean.configurable;

import beSen.bean.aop.AutowiredCapableBeanFactory;
import beSen.bean.aop.BeanPostProcessor;
import beSen.bean.factory.ListableBeanFactory;

/**
 * 扩展的接口（前置后置功能，提前注册到单例容器）
 * @author 康盼Java开发工程师
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowiredCapableBeanFactory {

    /**
     * 添加 BeanPostProcessor 接口的子类，提供前置及后置处理
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * getBean的时候调用，提前注册到单例容器
     */
    void preInstantiateSingletons();
}
