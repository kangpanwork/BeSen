package beSen.bean.InstantiationStrategy.impl;

import beSen.bean.InstantiationStrategy.InstantiationStrategy;
import beSen.bean.definition.BeanDefinition;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * javassist
 */
public class JavassistSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {

        try {
            ProxyFactory proxyFactory = new ProxyFactory();
            Class proxyClass = proxyFactory.createClass();
            ProxyObject proxyObject = null;
            proxyObject = (ProxyObject) proxyClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            proxyObject.setHandler(new JavassistMethodHandler());
            return proxyObject;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private class JavassistMethodHandler implements MethodHandler {
        /**
         * Is called when a method is invoked on a proxy instance associated
         * with this handler.  This method must process that method invocation.
         *
         * @param self       the proxy instance.
         * @param thisMethod the overridden method declared in the super
         *                   class or interface.
         * @param proceed    the forwarder method for invoking the overridden
         *                   method.  It is null if the overridden method is
         *                   abstract or declared in the interface.
         * @param args       an array of objects containing the values of
         *                   the arguments passed in the method invocation
         *                   on the proxy instance.  If a parameter type is
         *                   a primitive type, the type of the array element
         *                   is a wrapper class.
         * @return the resulting value of the method invocation.
         * @throws Throwable if the method invocation fails.
         */
        @Override
        public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
            return thisMethod.invoke(self, args);
        }
    }
}
