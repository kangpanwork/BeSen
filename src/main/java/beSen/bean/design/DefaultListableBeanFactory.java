package beSen.bean.design;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String,Class> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, Class cls) {
        beanDefinitionMap.put(beanName,cls);
    }

    @Override
    protected Class getBeanClass(String beanName) {
        return beanDefinitionMap.get(beanName);
    }
}
