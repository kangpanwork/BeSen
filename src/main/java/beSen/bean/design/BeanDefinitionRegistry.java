package beSen.bean.design;

/**
 * 注册 bean
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, Class cls);
}
