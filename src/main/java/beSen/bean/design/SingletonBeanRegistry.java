package beSen.bean.design;

/**
 * 单例注册接口
 */
public interface SingletonBeanRegistry {
    /**
     * 根据名称获取bean
     *
     * @param beanName
     * @return
     */
    Object getSingletonBean(String beanName);


    /**
     * 注册bean
     *
     * @param beanName
     * @param obj
     */
    void addSingletonBean(String beanName,Object obj);
}
