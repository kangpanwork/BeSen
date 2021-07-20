package beSen.test.aop;

import beSen.aop.AdvisedSupport;
import beSen.aop.BsJavassist;
import beSen.aop.BsJavassistDynamicProxy;
import beSen.aop.CglibAopProxy;
import beSen.aop.JavassistAopProxy;
import beSen.aop.JdkDynamicAopProxy;
import beSen.aop.BsProxyFactory;
import beSen.aop.TargetSource;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author 康盼Java开发工程师
 */
public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    private BsJavassist bsJavassist;

    @Before
    public void setAdvisedSupport() {
        advisedSupport = new AdvisedSupport();
        IService service = new ServiceImpl();
        ServiceInterceptor serviceInterceptor = new ServiceInterceptor();
        TargetSource targetSource = new TargetSource(service);
        advisedSupport.setMethodInterceptor(serviceInterceptor);
        advisedSupport.setTargetSource(targetSource);
        bsJavassist = new BsJavassist();
        bsJavassist.setClassName("beSen.test.aop.DynamicModel");
        bsJavassist.setField("private String name;");
        bsJavassist.setInterfaces(IService.class.getName());
        bsJavassist.setBody("public String getName(String name) {return name;};");
        bsJavassist.setInsertBefore("System.out.println(\"do something before...\");");
        bsJavassist.setInsertAfter("System.out.println(\"do something after...\");");

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
        IService proxy = (IService) new BsProxyFactory(advisedSupport).getProxy();
        proxy.getName();
        // 使用CGLIB动态代理
        advisedSupport.setProxyTargetClass(true);
        proxy = (ServiceImpl) new BsProxyFactory(advisedSupport).getProxy();
        proxy.getName();

    }

    @Test
    public void testJavassistAopProxy() throws Exception {
        IService proxy = (IService) new JavassistAopProxy(advisedSupport).getProxy();
        proxy.getName();
    }

    @Test
    public void testJavassistDynamicProxy() throws Exception {
        BsJavassistDynamicProxy bsJavassistDynamicProxy = new BsJavassistDynamicProxy(bsJavassist);
        CtClass ctClass = bsJavassistDynamicProxy.makeClass();
        CtMethod ctMethod = ctClass.getDeclaredMethod("getName");
        ctMethod.insertBefore(bsJavassist.getInsertBefore());
        ctMethod.insertAfter(bsJavassist.getInsertAfter());
        ctClass.writeFile("../");
        Class cls = ctClass.toClass();
        IService proxy = (IService) cls.newInstance();
        Method method = proxy.getClass().getMethod("getName",String.class);
        System.out.println(method.invoke(proxy,"hello"));
    }
}
