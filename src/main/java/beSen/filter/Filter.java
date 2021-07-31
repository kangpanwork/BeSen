package beSen.filter;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author 康盼Java开发工程师
 */
public interface Filter {

    /**
     * doFilter
     *
     * @param methodInvocation
     * @param filterChain
     */
    void doFilter(MethodInvocation methodInvocation, FilterChain filterChain);
}
