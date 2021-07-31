package beSen.test.filter;

import beSen.filter.Filter;
import beSen.filter.FilterChain;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author 康盼Java开发工程师
 */
public class SecondFilter implements Filter {

    @Override
    public void doFilter(MethodInvocation methodInvocation, FilterChain filterChain) {
        try {
            methodInvocation.proceed();
            filterChain.doFilter(methodInvocation, filterChain);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
