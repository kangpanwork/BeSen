package beSen.bean.factory.impl;

import beSen.bean.InstantiationStrategy.InstantiationStrategy;
import beSen.bean.InstantiationStrategy.impl.CglibSubclassingInstantiationStrategy;
import beSen.bean.definition.BeanDefinition;
import beSen.bean.definition.BeanReference;
import beSen.bean.definition.PropertyValue;
import beSen.bean.definition.PropertyValues;
import beSen.bean.factory.AbstractBeanFactory;

import java.lang.reflect.Constructor;

/**
 * createBean 的主要实现抽象类
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy;

    public InstantiationStrategy getInstantiationStrategy() {
        /**
         * 这里先写死，默认是CGLIB,以后修改动态获取
         */
        instantiationStrategy = new CglibSubclassingInstantiationStrategy();
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
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
        applyPropertyValues(beanName, bean, beanDefinition);
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
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,ctor,args);
    }


    /**
     * 然后给创建的bean 属性填充
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected  void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                String rbn = beanReference.getBeanName();
                value = getBean(rbn);
            }


        }
    }
}
