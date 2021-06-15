package beSen.aop;


import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;

public class JavassistAopProxy implements AopProxy {

    private ProxyFactory proxyFactory;

    private final AdvisedSupport advisedSupport;

    public JavassistAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() throws Exception{
        proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(advisedSupport.getTargetSource().getTargetClass());
        Class proxyClass = proxyFactory.createClass();
        ProxyObject proxyObject = (ProxyObject) proxyClass.newInstance();
        proxyObject.setHandler(new JavassistMethodHandler());
        return proxyObject;
    }


    private class JavassistMethodHandler implements MethodHandler {
        /**
         * Is called when a method is invoked on a proxy instance associated
         * with this handler.  This method must process that method invocation.
         *
         * @param self          the proxy instance.
         * @param thisMethod    the overridden method declared in the super
         *                      class or interface.
         * @param proceed       the forwarder method for invoking the overridden
         *                      method.  It is null if the overridden method is
         *                      abstract or declared in the interface.
         * @param args          an array of objects containing the values of
         *                      the arguments passed in the method invocation
         *                      on the proxy instance.  If a parameter type is
         *                      a primitive type, the type of the array element
         *                      is a wrapper class.
         * @return              the resulting value of the method invocation.
         *
         * @throws Throwable    if the method invocation fails.
         */
        @Override
        public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
            MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            if (methodInterceptor != null) {
                // proceed.invoke(advisedSupport.getTargetSource().getTarget(), args);
                return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), thisMethod, args));
            }
            return thisMethod.invoke(advisedSupport.getTargetSource().getTarget(), args);
        }
    }

}
