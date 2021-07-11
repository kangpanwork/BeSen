    rpc-server: 服务端封装
        RpcServerConfig：服务配置类
            HTTPTransportServer：默认服务实例类
            JSONEncoder：序列化实例类
            JSONDecoder：反序列化实例类
            port：监听端口
        ServiceInstance：服务的实例 --> 哪个对象暴露出哪个方法
            target：对象
            method：方法
        ServiceManager：管理rpc的所有服务
            Map<ServiceDescriptor, ServiceInstance> services：要将服务描述，服务实例作为key-value存储，便于客户端传来时，能够找到准确地实例，调用正确的方法
            register：服务注册【register(Class<T> interfaceClass, T bean) 】
                接口类 + 对象bean：将对象中的每一个方法都当做一个ServiceInstance注册进map中
            lookup：服务查找【lookUp(Request request)】
                获取请求中的ServiceDescriptor，去map中取出
        ServiceInvoke：【服务的调用】
            invoke(ServiceInstance serviceInstance, Request request):
                通过request的ServiceDescriptor找到服务的实例
                通过反射调用方法，传入参数
                ReflectionUtils.invoke(serviceInstance.getTarget(), serviceInstance.getMethod(),request.getParameters())
        RPCServer：【服务的封装】
            1.设置RpcServerConfig config
            2.反射获取网络实例     ReflectionUtils.newInstance(config.getTransportClass());
            3.反射获取序列化实例   ReflectionUtils.newInstance(config.getEncoderClass());
            4.反射获取反序列化实例 ReflectionUtils.newInstance(config.getDecoderClass());
            5.创建服务调用对象： this.serviceInvoke = new ServiceInvoke();
            6.创建服务管理对象：this.serviceManager = new ServiceManager();
            7.初始化网络实例：this.net.init(config.getPort(), this.handler); 此时只是准备好服务信息，并未开启监听
            
            8.register：服务注册 register(Class<T> interfaceClass, T bean) {serviceManager.register(interfaceClass, bean); }
            9.start：this.net.start 开启
            10.stop：this.net.stop  关闭
            11.handler请求处理：
                1.接收inputStream
                2.反序列化获得Request
                3.根据ServiceManager.lookup(request)找到实例ServiceInstance
                4.通过Object invoke = serviceInvoke.invoke(sis, request);得到响应结果并封装到 response.setData(invoke);
                5.序列化Response
                6.write