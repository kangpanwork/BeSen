package beSen.bean.design;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例注册实现
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> beans = new ConcurrentHashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return beans.get(beanName);
    }

    @Override
    public void addSingletonBean(String beanName, Object obj) {
        beans.put(beanName,obj);
    }
}
