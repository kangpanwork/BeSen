package beSen.bean.design;

/**
 * 制造 bean 工厂
 */
public interface BeanFactory {

    /**
     * 根据名称获取bean
     *
     * @param BeanName
     * @param args 构造方法的参数值
     * @return
     */
    Object getBean(String BeanName,Object... args);
}
