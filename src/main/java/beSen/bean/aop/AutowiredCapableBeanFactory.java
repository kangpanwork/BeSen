package beSen.bean.aop;



/**
 * @author 康盼Java开发工程师
 */
public interface AutowiredCapableBeanFactory {

    /**
     * 前置处理
     *
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName);

    /**
     * 后置处理
     *
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);
}
