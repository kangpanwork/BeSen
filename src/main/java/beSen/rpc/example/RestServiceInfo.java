package beSen.rpc.example;

import java.lang.reflect.Method;

/**
 * 请求服务的信息
 *
 * @author 康盼Java开发工程师
 */
public class RestServiceInfo {

    private Object target;
    private Method method;
    private Object[] args;

    public RestServiceInfo() {
    }

    public RestServiceInfo(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

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

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
