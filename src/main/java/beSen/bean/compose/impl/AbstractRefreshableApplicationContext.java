package beSen.bean.compose.impl;

import beSen.bean.factory.impl.DefaultListableBeanFactory;
import beSen.bean.support.ConfigurableListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }


    @Override
    protected void refreshBeanFactory() {
        beanFactory = new DefaultListableBeanFactory();
        loadBeanDefinitions(beanFactory);
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
