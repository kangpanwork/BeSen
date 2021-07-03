package beSen.bean.compose.impl;

import beSen.bean.aop.BeanFactoryPostProcessor;
import beSen.bean.aop.BeanPostProcessor;
import beSen.bean.compose.ConfigurableApplicationContext;
import beSen.bean.resource.impl.DefaultResourceLoader;
import beSen.bean.configurable.ConfigurableListableBeanFactory;

import java.util.Map;


/**
 * 4(实现了5的refresh方法，调用了3的getBeanFactory和refreshBeanFactory方法)->5
 *
 *
 * 组合功能
 * 1.BeanFactory 的 getBean方法
 * 2.提供资源加载器 DefaultResourceLoader
 * 3.创建BeanFactory （读取XML 注册bean信息）
 * 4.获取BeanFactory
 * 5.根据 BeanPostProcessor 类型获取实现的子类，调用他们的方法执行前置及后置处理
 * 6.提供registerBeanPostProcessors方法，添加前置及后置处理放入集合，提供getBean的时候调用
 *
 *
 * @author 康盼Java开发工程师
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() {
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        invokeBeanFactoryPostProcessors(beanFactory);
        registerBeanPostProcessors(beanFactory);
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 提供给子类实现，抽象出来
     */
    protected abstract void refreshBeanFactory();

    /**
     * 抽象出来给子类使用
     * 获取BeanFactory
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }


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

    /**
     * BeanFactory接口的方法
     *
     * @param name 注册的名字
     * @return
     */

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    /**
     * BeanFactory接口的方法
     *
     * @param name 注册的名字
     * @param args 构造方法的参数
     * @return
     */
    @Override
    public Object getBean(String name, Object... args)  {
        return getBeanFactory().getBean(name, args);
    }


}
