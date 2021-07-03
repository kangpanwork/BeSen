package beSen.bean.factory.impl;

import beSen.bean.InstantiationStrategy.InstantiationStrategy;
import beSen.bean.InstantiationStrategy.impl.CglibSubclassingInstantiationStrategy;
import beSen.bean.aop.AutowiredCapableBeanFactory;
import beSen.bean.aop.BeanPostProcessor;
import beSen.bean.definition.BeanDefinition;
import beSen.bean.definition.BeanReference;
import beSen.bean.definition.PropertyValue;
import beSen.bean.definition.PropertyValues;
import beSen.bean.factory.AbstractBeanFactory;
import cn.hutool.core.bean.BeanUtil;

import java.lang.reflect.Constructor;

/**
 * createBean 的主要实现抽象类
 *
 * 后期增加了AutowireCapableBeanFactory 增加前置后置处理
 * @author 康盼Java开发工程师
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowiredCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy;

    /**
     * 使用的是CGLIB
     *
     * @return
     */
    public InstantiationStrategy getInstantiationStrategy() {
        instantiationStrategy = new CglibSubclassingInstantiationStrategy();
        return instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        /**
         * 创建bean
         */
        Object bean = createBeanInstance(beanDefinition, beanName, args);
        /**
         * 给 Bean 填充属性
         */
        applyPropertyValues(bean, beanDefinition);
        /**
         * 前置后置处理
         */
        bean = initializeBean(beanName, bean, beanDefinition);
        /**
         * 注册到单例容器里面
         */
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 创建bean
     *
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        // 先判断参数和构造参数的数量是不是一样的
        Constructor ctor = null;
        Class clazz = beanDefinition.getBeanClass();
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            if (args != null && constructor.getParameterTypes().length == args.length) {
                ctor = constructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, ctor, args);
    }


    /**
     * 然后给创建的bean 属性填充
     *
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                // BeanReference 包装了，可以来标识是一个类，这样可以根据它的名字来获取实例
                BeanReference beanReference = (BeanReference) value;
                String beanName = beanReference.getBeanName();
                value = getBean(beanName);
            }
            // 属性填充
            BeanUtil.setFieldValue(bean, name, value);
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    /**
     * 1. 执行 BeanPostProcessor Before 处理
     *
     * @param existingBean
     * @param beanName
     * @return
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 2. 执行 BeanPostProcessor After 处理
     *
     * @param existingBean
     * @param beanName
     * @return
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }


}
