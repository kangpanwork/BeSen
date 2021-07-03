package beSen.bean.registry;

import beSen.bean.definition.BeanDefinition;

/**
 * 将bean的信息注册
 */
public interface BeanDefinitionRegistry {

    /**
     * BeanDefinition 注册
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
