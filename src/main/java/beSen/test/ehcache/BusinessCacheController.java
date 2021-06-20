package beSen.test.ehcache;

import beSen.aop.AdvisedSupport;
import beSen.aop.BsProxyFactory;
import beSen.aop.TargetSource;
import beSen.ehcache.BsEhcache;
import beSen.ehcache.Ehcache;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 从业务中抽离缓存
 */
public class BusinessCacheController {

    private BsEhcache bsEhcache;

    public BusinessCacheController(BsEhcache bsEhcache) {
        this.bsEhcache = bsEhcache;
    }

    public BusinessController getBusinessController() throws Exception {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        BusinessController businessController = new BusinessController(bsEhcache);
        TargetSource targetSource = new TargetSource(businessController);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(new BusinessControllerInterceptor());
        advisedSupport.setProxyTargetClass(true);
        return (BusinessController) new BsProxyFactory(advisedSupport).getProxy();
    }

    private class BusinessControllerInterceptor implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            String cache = bsEhcache.get(Ehcache.CACHE_NAME, "ID");
            if (null == cache || cache.isEmpty()) {
                bsEhcache.put(Ehcache.CACHE_NAME, "ID", "value");
                return invocation.proceed();
            }
            System.out.println("直接从缓存中返回");
            return cache;
        }
    }
}
