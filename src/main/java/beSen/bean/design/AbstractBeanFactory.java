package beSen.bean.design;

/**
 * 获取bean 先从注册的单例里面获取，没有的话创建，然后注册到单例容器里面
 * 这里抽象两个方法，1.创建bean 2.获取bean类型
 */
public abstract  class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName, Object... args) {
        Object obj = getSingletonBean(beanName);
        if (obj != null) {
            return obj;
        }
        Class cls = getBeanClass(beanName);
        return createBean(cls,beanName,args);
    }

    protected abstract Class getBeanClass(String beanName);
    protected abstract Object createBean(Class cls,String beanName,Object... args);
}
