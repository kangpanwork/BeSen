package beSen.bean.InstantiationStrategy.impl;

import beSen.bean.InstantiationStrategy.InstantiationStrategy;
import beSen.bean.definition.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (ctor != null) {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);

            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
           e.printStackTrace();
           throw new RuntimeException("Failed to instantiate [" + clazz.getName() + "]");
        }
    }
}
