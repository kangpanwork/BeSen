package beSen.component;

import beSen.filter.Filter;
import beSen.filter.FilterChain;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import sun.misc.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class WriteResponse implements Filter {

    @Autowired
    Environment environment;

    @Override
    public void doFilter(MethodInvocation methodInvocation, FilterChain filterChain) {
        try {
            methodInvocation.proceed();
            String port = environment.getProperty("local.server.port");
            String url = "http://localhost:" + port;
            String message = "上下文已经准备完毕的时候做一些逻辑";
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(message.getBytes("utf-8"));
            filterChain.doFilter(methodInvocation, filterChain);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
