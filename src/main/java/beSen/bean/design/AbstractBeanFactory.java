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

    /**
     * getBeanClass
     *
     * @param beanName
     * @return
     */
    protected abstract Class getBeanClass(String beanName);

    /**
     * createBean
     *
     * @param cls 类型
     * @param beanName 注册的bean名
     * @param args 参数名
     * @return
     */
    protected abstract Object createBean(Class cls,String beanName,Object... args);
}
