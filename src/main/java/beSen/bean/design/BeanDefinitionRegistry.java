package beSen.bean.design;

/**
 * 注册 bean
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册bean信息
     *
     * @param beanName
     * @param cls
     */
    void registerBeanDefinition(String beanName, Class cls);
}
