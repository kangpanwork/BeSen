package beSen.bean.readerRegistry;

import beSen.bean.registry.BeanDefinitionRegistry;
import beSen.bean.resource.ResourceLoader;

/**
 * 此接口提供了从资源读取bean信息，然后将bean信息注册到容器里
 * 组合两个功能，读取资源，注册容器
 *
 * 在没有此功能诞生前，使用的是硬编码方式去构造PropertyValues,然后填充bean信息，
 * 现在可以加载资源去构造PropertyValues（XML形式），然后填充bean信息
 * 为了组合这些功能，所以诞生了此接口
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();
}
