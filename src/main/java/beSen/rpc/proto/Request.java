package beSen.rpc.proto;

import java.util.Arrays;

/**
 * 表示一个RPC请求
 *
 * @author 康盼Java开发工程师
 */
public class Request {

    /**
     * 请求描述
     */
    private ServiceDescriptor serviceDescriptor;
    /**
     * 请求参数
     */
    private Object[] parameters;

    public ServiceDescriptor getServiceDescriptor() {
        return serviceDescriptor;
    }

    public void setServiceDescriptor(ServiceDescriptor serviceDescriptor) {
        this.serviceDescriptor = serviceDescriptor;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Request{" +
                "serviceDescriptor=" + serviceDescriptor +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
