package beSen.rpc.server;

import java.lang.reflect.Method;

/**
 * 表示一个具体的服务
 *
 * @author 康盼Java开发工程师
 */
public class ServiceInstance {

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 方法
     */
    private Method method;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ServiceInstance(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public ServiceInstance() {
    }
}
