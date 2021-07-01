package beSen.bean.readerRegistry.impl;


import beSen.bean.registry.BeanDefinitionRegistry;
import beSen.bean.resource.ResourceLoader;

/**
 * 组合了AbstractBeanDefinitionReader类的功能，提供XML解析， 注册 BeanDefinition
 */
public abstract class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }


}
