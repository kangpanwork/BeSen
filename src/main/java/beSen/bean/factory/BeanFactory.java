package beSen.bean.factory;

public interface BeanFactory {
    /**
     * 根据名字获取
     *
     * @param name 注册的名字
     * @return
     */
    Object getBean(String name);

    /**
     * 根据名字获取
     *
     * @param name 注册的名字
     * @param args 构造方法的参数
     * @return
     */
    Object getBean(String name, Object... args);

}
