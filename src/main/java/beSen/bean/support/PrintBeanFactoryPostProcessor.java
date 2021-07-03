package beSen.bean.support;

import beSen.bean.aop.BeanFactoryPostProcessor;
import beSen.bean.configurable.ConfigurableListableBeanFactory;

/**
 * @author 康盼Java开发工程师
 */
public class PrintBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out.println(">>> postProcessBeanFactory print");
    }
}
