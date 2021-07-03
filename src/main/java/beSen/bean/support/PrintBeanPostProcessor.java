package beSen.bean.support;

import beSen.bean.aop.BeanPostProcessor;

/**
 * @author 康盼Java开发工程师
 */
public class PrintBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("postProcessBeforeInitialization: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("postProcessAfterInitialization: " + beanName);
        return bean;
    }
}
