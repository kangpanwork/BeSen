package beSen.bean.readerRegistry.impl;

import beSen.bean.readerRegistry.BeanDefinitionReader;
import beSen.bean.registry.BeanDefinitionRegistry;
import beSen.bean.resource.ResourceLoader;
import beSen.bean.resource.impl.DefaultResourceLoader;

/**
 * BeanDefinitionReader 的实现类
 * 提供了“将bean的信息注册”这个功能
 * 提供了资源加载功能，并且初始化了 DefaultResourceLoader 这个类
 *
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry,new DefaultResourceLoader());
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
