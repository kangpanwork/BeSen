package beSen.test.aop;

import beSen.aop.AdvisedSupport;
import beSen.aop.CglibAopProxy;
import beSen.aop.JdkDynamicAopProxy;
import beSen.aop.ProxyFactory;
import beSen.aop.TargetSource;
import org.junit.Before;
import org.junit.Test;

public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void setAdvisedSupport() {
        advisedSupport = new AdvisedSupport();
        IService service = new ServiceImpl();
        ServiceInterceptor serviceInterceptor = new ServiceInterceptor();
        TargetSource targetSource = new TargetSource(service);
        advisedSupport.setMethodInterceptor(serviceInterceptor);
        advisedSupport.setTargetSource(targetSource);
    }

    @Test
    public void testJdkDynamicProxy() {
        IService proxy = (IService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.getName();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        ServiceImpl proxy = (ServiceImpl) new CglibAopProxy(advisedSupport).getProxy();
        proxy.getName();
    }

    @Test
    public void testProxyFactory() throws Exception {
        // 使用JDK动态代理
        advisedSupport.setProxyTargetClass(false);
        IService proxy = (IService) new ProxyFactory(advisedSupport).getProxy();
        proxy.getName();
        // 使用CGLIB动态代理
        advisedSupport.setProxyTargetClass(true);
        proxy = (ServiceImpl) new ProxyFactory(advisedSupport).getProxy();
        proxy.getName();

    }
}
