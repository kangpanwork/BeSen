package beSen.bean.InstantiationStrategy;

import beSen.bean.definition.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略，可以是反射，JDK动态代理，CGLIB动态代理
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args);
}
