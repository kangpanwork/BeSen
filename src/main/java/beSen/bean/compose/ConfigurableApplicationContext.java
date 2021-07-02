package beSen.bean.compose;

/**
 * 提供刷新容器功能
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh();
}
