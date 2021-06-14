package beSen.aop;

import org.aopalliance.intercept.MethodInterceptor;

public class AdvisedSupport {
    private TargetSource targetSource;
    private MethodInterceptor methodInterceptor; // 方法拦截器

    //是否使用cglib代理
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
