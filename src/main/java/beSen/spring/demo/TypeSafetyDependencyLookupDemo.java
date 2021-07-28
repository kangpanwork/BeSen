package beSen.spring.demo;

import beSen.xsd.design.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找示列
 *
 * @author 康盼Java开发工程师
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        applicationContext.refresh();
        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderIfAvailable(applicationContext);
        displayListableBeanFactoryGetBeanOfType(applicationContext);
        displayObjectProviderStreamOps(applicationContext);
        applicationContext.close();
    }

    /**
     * 演示 ObjectProvider#Stream 集合类型查找的安全性 安全
     *
     * {@link ObjectProvider#stream()}
     */
    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps",() -> userObjectProvider.forEach(System.out::println));
    }

    /**
     * 演示 ListableBeanFactory#getBean 集合类型查找的安全性 安全
     *
     * {@link ListableBeanFactory#getBeansOfType(Class)}
     */
    private static void displayListableBeanFactoryGetBeanOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactoryGetBean",() -> beanFactory.getBeansOfType(User.class));
    }

    /**
     * 演示 ObjectProvider#getIfAvailable 单一类型查找的安全性 安全
     *
     * {@link ObjectProvider#getIfAvailable()}
     */
    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable",() -> userObjectProvider.getIfAvailable());
    }

    /**
     * 演示 ObjectFactory#getObject 单一类型查找的安全性 不安全
     *
     * {@link ObjectFactory#getObject()}
     */
    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", () -> userObjectFactory.getObject());
    }

    /**
     * 演示 BeanFactory#getBean 单一类型查找的安全性 不安全
     *
     * {@link BeanFactory#getBean(Class)}
     */
    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean",() -> beanFactory.getBean(User.class));
    }

    /**
     * printBeansException
     *
     * @param message
     * @param runnable
     */
    private static void printBeansException(String message, Runnable runnable) {
        System.err.println("exception from:" + message);
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }


}
