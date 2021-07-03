package beSen.bean.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * SingletonBeanRegistry 的实现类
 * @author 康盼Java开发工程师
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
