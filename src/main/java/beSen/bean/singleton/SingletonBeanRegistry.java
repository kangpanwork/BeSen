package beSen.bean.singleton;


/**
 * @author 康盼Java开发工程师
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例
     *
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

}
