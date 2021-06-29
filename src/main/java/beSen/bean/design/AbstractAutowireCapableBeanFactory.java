package beSen.bean.design;

import beSen.aop.CglibAopProxy;

import java.lang.reflect.Constructor;

/**
 * CGLIB创建bean
 * 主要实现了父类的创建bean抽象方法
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private CglibAopProxy proxy;

    @Override
    protected Object createBean(Class cls,String beanName,Object... args) {
        Object obj = createBeanInstance(cls,args);
        addSingletonBean(beanName,obj);
        return obj;
    }

    protected Object createBeanInstance(Class cls,Object[] args) {
        proxy = new CglibAopProxy(null);
        Constructor defaultConstructor = null;
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            if (args != null && constructor.getParameterTypes().length == args.length) {
                defaultConstructor = constructor;
                break;
            }
        }
        return proxy.getProxy(cls,defaultConstructor,args);
    }
}
