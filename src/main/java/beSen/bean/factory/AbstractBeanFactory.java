package beSen.bean.factory;

import beSen.bean.definition.BeanDefinition;
import beSen.bean.singleton.DefaultSingletonBeanRegistry;

/**
 * 工厂抽象化一层，主要支撑单例获取和存储
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 核心
     * 这里对外抽象了两个方法，一是getBeanDefinition，二是createBean
     * 当单例容器中有的时候就直接返回，没有就去定义的bean中获取相关信息，然后创建bean
     *
     * @param name
     * @param args
     * @param <T>
     * @return
     */
    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    /**
     * 获取bean定义的信息（类型，属性，属性值）
     * 属性和属性值可以来源于XML，解析XML然后赋值给对应的属性
     * @param name
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String name);

    /**
     * 创建bean 可用反射，动态代理创建（JDK动态代理，CGLIB动态代理等）
     *
     * @param name
     * @param beanDefinition
     * @param args
     * @return
     */
    protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);

    @Override
    public Object getBean(String name) {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name,args);
    }
}
