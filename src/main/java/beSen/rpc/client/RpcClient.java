package beSen.rpc.client;

import beSen.rpc.proto.Peer;
import beSen.rpc.proto.Request;
import beSen.rpc.proto.Response;
import beSen.rpc.proto.ServiceDescriptor;
import beSen.rpc.transport.TransportClient;
import com.alibaba.fastjson.JSON;
import org.eclipse.jetty.http.HttpStatus;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Rpc 客户端
 *
 * @author 康盼Java开发工程师
 */
public class RpcClient {

    private TransportSelector selector;

    public RpcClient() {
        this(Arrays.asList(new Peer("127.0.0.1", 3000)),1);
    }

    public RpcClient(List<Peer> servers, int connectCount) {
        this.selector = new RandomTransportSelector();
        this.selector.init(servers,connectCount);
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{clazz},new RemoteHandler(clazz));
    }

    private class RemoteHandler implements InvocationHandler {

        private Class clazz;

        public RemoteHandler(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)  {
            Request request = new Request();
            request.setServiceDescriptor(ServiceDescriptor.from(clazz,method));
            request.setParameters(args);

            Response response = remoteInvoke(request);
            System.out.println(String.format(Locale.ROOT,"response:{%s}" ,response));
            if (response != null && HttpStatus.OK_200 == response.getCode()) {
               return response.getData();
            } else {
                throw new IllegalStateException("fail to invoke remote:" + response);
            }
        }
    }

    private Response remoteInvoke(Request request) {
        TransportClient client = null;
        Response response;
        try {
            client = selector.select();
            byte[] bytes = JSON.toJSONBytes(request);
            InputStream inputStream = client.write(new ByteArrayInputStream(bytes));
            byte[] b = IOUtils.readFully(inputStream, inputStream.available(), true);
            response = JSON.parseObject(b, Response.class);
        } catch (Exception e) {
            response = new Response();
            response.setCode(HttpStatus.BAD_REQUEST_400);
            response.setMessage("RpcClient get Error!");
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return response;
    }
}
