package beSen.bean.factory.impl;

import beSen.bean.definition.BeanDefinition;
import beSen.bean.registry.BeanDefinitionRegistry;
import beSen.bean.support.ConfigurableListableBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanFactory提供了根据名称获取bean接口
 *
 * AbstractBeanFactory组合了从单例容器中获取bean
 * 抽象了根据bean名称获取bean信息，然后创建bean
 *
 * AbstractAutowireCapableBeanFactory，组合了AbstractBeanFactory类功能， 实现了创建bean的方法
 * 提供了属性填充和单例存储功能
 *
 * DefaultListableBeanFactory组合了AbstractAutowireCapableBeanFactory功能，同时实现了根据bean名称获取bean信息方法
 * 继承了BeanDefinitionRegistry 提供了注册bean信息的功能
 *
 * 后期，增加了ConfigurableListableBeanFactory接口 实现它的 getBeansOfType 方法
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String name) {
        BeanDefinition beanDefinition =  beanDefinitionMap.get(name);
        if(beanDefinition == null) {
            throw new RuntimeException("No bean named '\" + beanName + \"' is defined");
        }
        return beanDefinition;
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }



}
