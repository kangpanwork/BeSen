package beSen.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 封装AOP需要的参数，源对象，方法拦截器（用于AOP拦截，方法前置处理及方法后置处理）
 * @author 康盼Java开发工程师
 */
public class AdvisedSupport {
    private TargetSource targetSource;
    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 是否使用cglib代理
     */
    private boolean proxyTargetClass = true;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }
}
