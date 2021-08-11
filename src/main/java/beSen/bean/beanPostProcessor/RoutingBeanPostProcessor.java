package beSen.bean.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class RoutingBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Injected.class)) {
                if (!field.getType().isInterface()) {
                    throw new RuntimeException("Injected field must be declared as an interface:" + field.getName() + "@Class" + clazz.getName());
                }
                try {
                    inject(field,bean,field.getType());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("inject error:" + e.getMessage());
                }
            }
        }
        return bean;
    }

    private void inject(Field field, Object bean, Class type) throws IllegalAccessException{
        Map<String, Object> beans = applicationContext.getBeansOfType(type);
        field.setAccessible(true);
        if (beans.size() == 1) {
            field.set(bean,beans.values().iterator().next());
        } else {
            Object proxy = RoutingBeanProxyFactory.createProxy(type,beans);
            field.set(bean, proxy);
        }
    }
}
