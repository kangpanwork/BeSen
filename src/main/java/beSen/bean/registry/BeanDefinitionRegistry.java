package beSen.bean.registry;

import beSen.bean.definition.BeanDefinition;

/**
 * 将bean的信息注册
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
