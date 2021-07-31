package beSen.config;

import beSen.filter.Filter;
import beSen.filter.FilterChain;
import beSen.filter.FilterInvocation;
import beSen.test.filter.IMessage;
import beSen.test.filter.Log;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
@Configuration
public class FilterRun implements Runnable {

    @Autowired(required = false)
    private List<Filter> components;


    private static FilterChain filterChain;

    static {
        if (null == filterChain) {
            synchronized (FilterChain.class) {
                if (null == filterChain) {
                    filterChain = new FilterChain();
                }
            }
        }
    }


    /**
     * 上下文已经准备完毕的时候触发
     */
    @EventListener({ApplicationReadyEvent.class})
    @Order(98)
    @Override
    public void run() {
        if(!CollectionUtils.isEmpty(components)) {
            for(Filter filter : components) {
                filterChain.addChain(filter);
            }
            filterChain.doFilter(methodInvocation(),filterChain);
        }
    }

    public MethodInvocation methodInvocation()  {
        try {
            IMessage message = new Log("记录日志");
            Method method = message.getClass().getMethod("getLog");
            return new FilterInvocation(message,method,null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
