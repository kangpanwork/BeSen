package beSen.bean.beanPostProcessor;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 康盼Java开发工程师
 */
public class RoutingBeanProxyFactory {
    public static Object createProxy(Class targetClass, Map<String, Object> beans) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(targetClass);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(targetClass,beans));
        return proxyFactory.getProxy();
    }

    static class VersionRoutingMethodInterceptor implements MethodInterceptor {

        private String version;

        private Object object;

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Method method = invocation.getMethod();
            String version = this.version;
            if (method.isAnnotationPresent(Version.class)) {
                version =  method.getAnnotation(Version.class).value();
            }
            if (StringUtils.isBlank(version)) {
                throw new IllegalStateException("version value is blank, method:" + method.getName());
            }
            return method.invoke(object,invocation.getArguments());
        }

        public VersionRoutingMethodInterceptor(Class targetClass, Map<String, Object> beans) {
            String interfaceName = StringUtils.uncapitalize(targetClass.getSimpleName());
            if (targetClass.isAnnotationPresent(Version.class)) {
                this.version = ((Version) targetClass.getAnnotation(Version.class)).value();
                this.object = beans.get(getBeanName(interfaceName,version));
            }
        }

        private String getBeanName(String interfaceName, String version) {
            return interfaceName + "Impl" + version;
        }
    }
}
