package beSen.bean.InstantiationStrategy;

import beSen.bean.definition.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略，可以是反射，JDK动态代理，CGLIB动态代理
 */
public interface InstantiationStrategy {

    /**
     * 实例化
     *
     * @param beanDefinition bean信息
     * @param beanName bean 名字
     * @param ctor 构造方法
     * @param args 构造参数
     * @return
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args);
}
