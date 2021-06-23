package beSen.bsSimpleServer;


/**
 * 2.维护核心组件
 * 它负责告知其子类，其核心业务逻辑应该全权委托核心组件完成
 * 自己仅仅是做增强处理
 */
public abstract class AbstractSimpleServlet implements Servlet{
    Servlet servlet;
    public AbstractSimpleServlet(Servlet servlet) {
        this.servlet = servlet;
    }
}
