package beSen.bean.compose.impl;

import beSen.bean.factory.impl.DefaultListableBeanFactory;
import beSen.bean.configurable.ConfigurableListableBeanFactory;

/**
 * 3（实现了4的refreshBeanFactory和getBeanFactory方法，调用了2的loadBeanDefinitions方法）->4
 * 组合了 DefaultListableBeanFactory 功能
 * 同时提供这个实例出去，方便子类调用
 *
 * 功能：主要实例化 DefaultListableBeanFactory，抽象注册BeanDefinitions信息功能
 *
 * @author 康盼Java开发工程师
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }


    /**
     * 等待refresh调用
     */
    @Override
    protected void refreshBeanFactory() {
        beanFactory = new DefaultListableBeanFactory();
        loadBeanDefinitions(beanFactory);
    }

    /**
     * 扩展XmlBeanDefinitionReader的loadBeanDefinitions方法
     * 将其进行抽象化，给子类实现，同时子类再回调XmlBeanDefinitionReader的loadBeanDefinitions方法
     *
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
