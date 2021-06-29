package beSen.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CglibAopProxy implements AopProxy {

    private final AdvisedSupport advisedSupport;

    public CglibAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
        return enhancer.create();
    }

    public Object getProxy(Class cls, Constructor constructor,Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new NoOp(){
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        // 不带参数的构造函数
        if(constructor == null) {
            return enhancer.create();
        }
        // 带参数的构造函数
        return enhancer.create(constructor.getParameterTypes(),args);
    }
    /**
     * 注意此处的 MethodInterceptor 是cglib中的接口
     * org.springframework.cglib.proxy.MethodInterceptor;
     *
     * AdvisedSupport 中的 MethodInterceptor 是 AOP 的
     * org.aopalliance.intercept.MethodInterceptor;
	 */
    private class DynamicAdvisedInterceptor implements MethodInterceptor {

        private AdvisedSupport advisedSupport;

        private DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advisedSupport = advisedSupport;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            CglibMethodInvocation cglibMethodInvocation = new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(),
                    method,objects, methodProxy);

            return advisedSupport.getMethodInterceptor().invoke(cglibMethodInvocation);
        }
    }

    /**
     * methodProxy 是 Cglib 调用
     *
     */
    private class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments,MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            // CGLIB$代理的方法$0()Ljava/lang/String;
            return methodProxy.invoke(getTarget(),getArguments()); // 利用的是 CGLIB 的
        }
    }
}
