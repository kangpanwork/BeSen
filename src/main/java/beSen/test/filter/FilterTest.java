package beSen.test.filter;

import beSen.filter.FilterChain;
import beSen.filter.FilterInvocation;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 康盼Java开发工程师
 */
public class FilterTest {

    private FilterInvocation filterInvocation;
    private IMessage log = new Log("hello world");

    @Test
    public void test()  {
        FilterChain filterChain = new FilterChain();
        filterChain.addChain(new FirstFilter()).addChain(new SecondFilter());
        IMessage message = (IMessage) Proxy.newProxyInstance(getClass().getClassLoader(), log.getClass().getInterfaces(), new LogHandler());
        message.getLog();
        filterChain.doFilter(filterInvocation,filterChain);
    }

    class LogHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            filterInvocation = new FilterInvocation(proxy,method,args);
            return method.invoke(log, args);
        }
    }
}
