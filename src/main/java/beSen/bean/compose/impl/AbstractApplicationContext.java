package beSen.bean.compose.impl;

import beSen.bean.aop.BeanFactoryPostProcessor;
import beSen.bean.aop.BeanPostProcessor;
import beSen.bean.compose.ConfigurableApplicationContext;
import beSen.bean.resource.impl.DefaultResourceLoader;
import beSen.bean.support.ConfigurableListableBeanFactory;

import java.util.Map;


/**
 * 组合功能
 * 1.addBeanPostProcessor 添加这些实现此接口的子类
 * 2.getBean
 * 3.资源加载
 * 4.刷新容器
 *  创建BeanFactory
 *  加载 BeanDefinition
 *  获取BeanFactory
 *  根据 BeanPostProcessor 类型获取实现的子类，调用他们的方法执行前置及后置处理
 *  提前实例化单例对象
 *
 *
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() {
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        invokeBeanFactoryPostProcessors(beanFactory);
        registerBeanPostProcessors(beanFactory);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args)  {
        return getBeanFactory().getBean(name, args);
    }

    /**
     * 提供给子类实现
     */
    protected abstract void refreshBeanFactory();

    /**
     * 获取BeanFactory
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }


}
