package beSen.filter;

import org.aopalliance.intercept.MethodInvocation;

import java.io.File;

/**
 *
 * @author 康盼Java开发工程师
 *
 */
public class FilterChain extends DefaultFilterRegistry implements Filter {

    private int count = 0;

    @Override
    public void doFilter(MethodInvocation methodInvocation, FilterChain filterChain) {
        if (count == chainList.size()) {
            return;
        }
        Filter chain = getChain(count);
        count++;
        chain.doFilter(methodInvocation,filterChain);
    }
}
