package beSen.bean.support;

import beSen.bean.aop.AutowiredCapableBeanFactory;
import beSen.bean.factory.ListableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowiredCapableBeanFactory, ConfigurableBeanFactory {
}
