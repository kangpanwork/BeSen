package beSen.test.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author 康盼Java开发工程师
 */
public class ServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("do something before...");
        Object result = invocation.proceed();
        System.out.println("do something after...");
        return result;
    }
}
