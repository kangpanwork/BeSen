package beSen.rpc.server;

import beSen.reflect.ReflectUtils;
import beSen.rpc.proto.Request;
import beSen.rpc.proto.ServiceDescriptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理RPC暴露的服务
 *
 * @author 康盼Java开发工程师
 */
public class ServiceManager {

    private Map<ServiceDescriptor,ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 服务注册
     *
     * @param interfaceClass
     * @param bean
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectUtils.getPublicMethods(interfaceClass);
        Arrays.stream(methods).forEach(method -> {
            ServiceDescriptor key = ServiceDescriptor.from(interfaceClass,method);
            ServiceInstance value = new ServiceInstance(bean,method);
            services.put(key,value);
            System.out.println(String.format(Locale.ROOT,"register service: {%s} {%s}",key.getClazz(), key.getMethod()));
        });
    }

    public ServiceInstance lookup(Request request) {
        return services.get(request.getServiceDescriptor());
    }

    public Map<ServiceDescriptor, ServiceInstance> getServices() {
        return services;
    }

    public void setServices(Map<ServiceDescriptor, ServiceInstance> services) {
        this.services = services;
    }
}
