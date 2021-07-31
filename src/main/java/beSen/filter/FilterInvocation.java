package beSen.filter;

import beSen.aop.ReflectiveMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author 康盼Java开发工程师
 */
public class FilterInvocation extends ReflectiveMethodInvocation {

    public FilterInvocation(Object target, Method method, Object[] arguments) {
        super(target, method, arguments);
    }

    @Override
    public Object proceed() throws Throwable {
        System.out.println("filter...");
        return super.proceed();
    }
}
