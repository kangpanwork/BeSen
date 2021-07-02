package beSen.bean.aop;



public interface AutowiredCapableBeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName);

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);
}
