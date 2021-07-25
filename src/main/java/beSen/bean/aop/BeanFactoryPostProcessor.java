package beSen.bean.aop;

import beSen.bean.configurable.ConfigurableListableBeanFactory;

/**
 * @author 康盼Java开发工程师
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
