package beSen.bsSimpleServer;

/**
 * 组件接口，装饰者和被装饰者的接口，它定义了被装饰者的核心功能和装饰者需要加强的功能点
 */
public interface Servlet {
    void service(SimpleRequest request, SimpleResponse response);
}
