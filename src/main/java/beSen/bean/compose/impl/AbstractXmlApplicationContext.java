package beSen.bean.compose.impl;

import beSen.bean.factory.impl.DefaultListableBeanFactory;
import beSen.bean.readerRegistry.impl.XmlBeanDefinitionReader;


/**
 * 2(实现了3的loadBeanDefinitions方法，调用1的getConfigLocation方法)->3
 * 使用本身（因为继承了DefaultResourceLoader）可以读取资源
 * 然后加载资源进行BeanDefinitions注册
 *
 * 主要组合两个功能
 * @author 康盼Java开发工程师
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /**
     * loadBeanDefinitions 等待refresh调用
     *
     * @param beanFactory
     */
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,this);
        String configLocation = getConfigLocation();
        if (configLocation != null && configLocation.length() != 0){
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        }
    }

    /**
     * 获取配置文件
     *
     * @return
     */
    protected abstract String getConfigLocation();
}
