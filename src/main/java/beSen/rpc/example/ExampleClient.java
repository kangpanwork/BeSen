package beSen.rpc.example;


import beSen.rpc.proto.Response;
import com.alibaba.fastjson.JSON;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.URL;

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
        byte[] bytes = JSON.toJSONBytes(restInfo);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        String url = "http://" + "127.0.0.1" + ":"  + "8080";
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        // 设置是否从httpUrlConnection读入，默认情况下是true
        httpURLConnection.setDoInput(true);
        // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true, 默认情况下是false;
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.connect();
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        // 此处getOutputStream会隐含的进行connect
        // 把对象字节转换inputStream,然后向此连接写入的输出流，
        OutputStream outputStream = httpURLConnection.getOutputStream();
        int n;
        byte[] buffer = new byte[1024*4];
        while (-1 != (n = inputStream.read(buffer))) {
            outputStream.write(buffer,0,n);
        }
        // 读取此连接响应的输出流
        InputStream in = httpURLConnection.getInputStream();
        byte[] b = IOUtils.readFully(in, in.available(), true);
        // 字节转换，返回结果
        Object result  = JSON.parseObject(b, Object.class);
        return result;
    }
}
