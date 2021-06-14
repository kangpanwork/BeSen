package beSen.test.aop;

import beSen.aop.AdvisedSupport;
import beSen.aop.JdkDynamicAopProxy;
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
}
