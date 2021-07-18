package beSen.rpc.example;


/**
 * 请求服务的信息
 *
 * @author 康盼Java开发工程师
 */
public class RestServiceInfo {

    private String clazz;
    private String method;
    private Object[] args;

    public RestServiceInfo() {
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
