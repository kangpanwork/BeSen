package beSen.aop;

/**
 * 代理工厂，根据 AdvisedSupport 属性  proxyTargetClass 用 JDK 代理还是 CGLIB
 */
public class BsProxyFactory {

    private AdvisedSupport advisedSupport;

    public BsProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() throws Exception{
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new CglibAopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
