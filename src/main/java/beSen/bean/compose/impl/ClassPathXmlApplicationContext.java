package beSen.bean.compose.impl;


/**
 * 设计步骤：
 * 实现一个简单的Bean容器
 * 运用设计模式，实现 Bean 的定义、注册、获取
 * 基于Cglib实现含构造函数的类实例化策略
 * 为Bean对象注入属性和依赖Bean的功能实现
 * 设计与实现资源加载器，从Spring.xml解析和注册Bean对象
 * 实现应用上下文，自动识别、资源加载、扩展机制
 * 向虚拟机注册钩子，实现Bean对象的初始化和销毁方法
 * 定义标记类型Aware接口，实现感知容器对象
 * 关于Bean对象作用域以及FactoryBean的实现和使用
 *
 * 1(实现了2的getConfigLocation方法,调用了refresh方法)
 * ->2(实现了3的loadBeanDefinitions方法，（抽象了）调用1的getConfigLocation方法)
 * ->3（实现了4的refreshBeanFactory和 getBeanFactory方法，（抽象了）调用了2的loadBeanDefinitions方法）
 * ->4(实现了5的refresh方法，（抽象了）调用了3的getBeanFactory和refreshBeanFactory方法)
 * ->5（提供了refresh方法）->
 *
 * 其中 refresh 包括 refreshBeanFactory 和 getBeanFactory方法
 *
 *
 * 使用xml构造自己，同时继承了读取xml的类，组合子类特性
 * refresh 使用了子类功能
 * @author 康盼Java开发工程师
 *
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private final String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected String getConfigLocation() {
        return configLocation;
    }
}
