package beSen.bean.compose;

/**
 * 5（提供了refresh方法）->
 *
 * 提供刷新容器功能
 * @author 康盼Java开发工程师
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh();
}
