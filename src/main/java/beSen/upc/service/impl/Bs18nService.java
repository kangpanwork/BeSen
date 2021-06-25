package beSen.upc.service.impl;

import beSen.aop.AdvisedSupport;
import beSen.aop.AopProxy;
import beSen.aop.BsProxyFactory;
import beSen.aop.TargetSource;
import beSen.upc.service.I18nService;
import org.springframework.stereotype.Service;

/**
 * 模拟本地调其它系统的服务，使用动态代理
 */
@Service
public class Bs18nService implements I18nService {

    /**
     * 外部服务固定开头
     */
    private final String EXTERNAL = "External";

    /**
     * 代理类
     */
    private AopProxy aopProxy;

    public Bs18nService() {
        try {
            AdvisedSupport advisedSupport = new AdvisedSupport();
            int lastIndex = this.getClass().getName().lastIndexOf(".");
            String packageName = this.getClass().getName().substring(0, lastIndex + 1);
            Class<?>[] classes = this.getClass().getInterfaces();
            Class c = classes[0];
            String interfaceName = c.getSimpleName();
            String className = packageName + EXTERNAL + interfaceName.substring(1);
            Class<?> cls = Class.forName(className);
            I18nService service = (I18nService) cls.newInstance();
            advisedSupport.setProxyTargetClass(false);
            TargetSource targetSource = new TargetSource(service);
            advisedSupport.setTargetSource(targetSource);
            aopProxy = new BsProxyFactory(advisedSupport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getLanguage() throws Exception{
        I18nService proxy = (I18nService) aopProxy.getProxy();
        return proxy.getLanguage();
    }
}
