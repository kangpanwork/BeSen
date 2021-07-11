package beSen.rpc.server;

import beSen.reflect.ReflectUtils;
import beSen.rpc.proto.Request;
import beSen.rpc.proto.Response;
import beSen.rpc.transport.HttpTransportServer;
import beSen.rpc.transport.RequestHandler;
import beSen.rpc.transport.TransportServer;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;


/**
 * @author 康盼Java开发工程师
 */
public class RpcServer {

    /**
     * 网络配置
     */
    private TransportServer net;
    /**
     * 服务管理
     */
    private ServiceManager serviceManager;

    public RpcServer() throws Exception {
        this(3000);
    }

    public RpcServer(int port) throws Exception{
        // 实例化 net
        this.net = new HttpTransportServer();
        this.net.init(port,this.handler2);
        // 实例化 service
        this.serviceManager = new ServiceManager();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() throws Exception {
        this.net.start();
    }

    public void stop() throws Exception {
        this.net.stop();
    }

    private RequestHandler handler = (inputStream, outputStream) -> {
        Response response = new Response();
        try {
            byte[] bytes = IOUtils.readFully(inputStream, inputStream.available(), true);
            Request request = JSON.parseObject(bytes,Request.class);
            System.out.println(String.format(Locale.ROOT,"Get Request: {%s}",request));
            serviceManager.getServices().forEach((k,v) -> {
                System.out.println("registerMap:" + k + " : " + v);
            });
            ServiceInstance serviceInstance = serviceManager.lookup(request);
            System.out.println(String.format(Locale.ROOT,"serviceInstance:{%s}" ,serviceInstance.toString()));
            Object obj = ReflectUtils.invoke(serviceInstance.getTarget(),serviceInstance.getMethod(),request.getParameters());
            response.setData(obj);
        } catch (Exception e) {
            response.setCode(HttpStatus.SC_BAD_REQUEST);
            response.setMessage("RpcServer get Error!" + e.getMessage());
        } finally {
            byte[] message = JSON.toJSONBytes(response);
            try {
                outputStream.write(message);
            } catch (IOException ioException) {
            }
        }
    };

    private RequestHandler handler2 = new RequestHandler() {
        @Override
        public void onRequest(InputStream inputStream, OutputStream outputStream) {
            Response response = new Response();
            try {
                byte[] bytes = IOUtils.readFully(inputStream, inputStream.available(), true);
                Request request = JSON.parseObject(bytes, Request.class);
                ServiceInstance sis = serviceManager.lookup(request);
                Object invoke =  ReflectUtils.invoke(sis.getTarget(),sis.getMethod(),request.getParameters());
                response.setData(invoke);
            } catch (Exception e) {
                response.setCode(HttpStatus.SC_BAD_REQUEST);
                response.setMessage("RpcServer get Error: " + e.getClass().getName() + "\n :" + e.getMessage());
            } finally {
                byte[] encode = JSON.toJSONBytes(response);
                try {
                    outputStream.write(encode);
                } catch (IOException e) {
                }
            }
        }
    };

}
