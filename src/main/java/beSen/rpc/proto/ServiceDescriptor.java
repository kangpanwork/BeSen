package beSen.rpc.proto;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 表示一个请求服务的信息描述
 *
 * @author 康盼Java开发工程师
 */
public class ServiceDescriptor {
    /**
     * 请求的类型
     */
    private String clazz;
    /**
     * 请求的方法
     */
    private String method;
    /**
     * 请求的返回值
     */
    private String returnType;
    /**
     * 请求的参数类型
     */
    private String[] parameterTypes;

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

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(String[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceDescriptor that = (ServiceDescriptor) o;
        return Objects.equals(this.toString(), that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * 通过 class method 获取服务的信息
     *
     * @param clazz
     * @param method
     * @return
     */
    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getName());
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] strParameterTypes = Arrays.stream(parameterTypes).map(type -> type.getName()).collect(Collectors.toList()).toArray(new String[0]);
        serviceDescriptor.setParameterTypes(strParameterTypes);
        System.out.println(String.format(Locale.ROOT,"serviceDescriptor:{%s}",serviceDescriptor));
        return serviceDescriptor;
    }
}
