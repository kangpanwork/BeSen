package beSen.bean.aop;

import beSen.bean.support.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
