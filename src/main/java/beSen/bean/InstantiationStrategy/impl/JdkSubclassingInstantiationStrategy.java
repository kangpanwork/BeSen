package beSen.bean.InstantiationStrategy.impl;

import beSen.bean.InstantiationStrategy.InstantiationStrategy;
import beSen.bean.definition.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理，必须有接口
 */
public class JdkSubclassingInstantiationStrategy implements InstantiationStrategy, InvocationHandler {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        return Proxy.newProxyInstance(beanDefinition.getBeanClass().getClassLoader(),beanDefinition.getBeanClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       return method.invoke(proxy, args);
    }
}
