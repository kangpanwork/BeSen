package beSen.spring.demo;

import beSen.xsd.design.model.User;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


/**
 *  通过 {@link ObjectProvider} 进行依赖查找
 *
 *
 * @author 康盼Java开发工程师
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类 Configuration Class
        applicationContext.register(ObjectProviderDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找集合对象
        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        applicationContext.close();
    }



    @Bean
    @Primary
    public String helloWorld() {
        return "Hello,World";
    }

    @Bean
    public String message() {
        return "Message";
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(() -> new User());
    }



    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        getException(() -> System.out.println(objectProvider.getObject()));
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        Iterable<String> iterable = objectProvider;
        for (String str :iterable) {
            System.out.println(str);
        }
        objectProvider.stream().forEach(System.out::println);
    }

    public static void getException(Runnable runnable) {
        try {
            runnable.run();
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException ){
            System.err.println(noSuchBeanDefinitionException.getMessage());
        }
    }
}
