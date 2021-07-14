package beSen.rpc.example;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 康盼Java开发工程师
 */
public class ExampleClient {

    public static void main(String[] args) throws Exception {
        CalcService calcService = (CalcService) Proxy.newProxyInstance(ExampleClient.class.getClassLoader(),new Class[]{CalcService.class},new RemoteHandler());
        int sum = calcService.add(1,2);
        System.out.println(sum);
    }
}
class RemoteHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RestServiceInfo restInfo = new RestServiceInfo(proxy,method,args);
        return null;
    }
}
