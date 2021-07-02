package beSen.bean.aop;

public interface BeanPostProcessor {
    /**
     * 前置处理
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * 后置处理AutowireCapableBeanFactory
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
