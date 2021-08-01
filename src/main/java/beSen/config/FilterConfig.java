package beSen.config;

import beSen.jwt.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 注册自定义过滤器可以设置这些自定义过滤器的执行顺序
 * @author 康盼Java开发工程师
 */
@Configuration
public class FilterConfig {

    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public FilterRegistrationBean<Filter> filter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(tokenFilter);
        filterRegistrationBean.setName("tokenFilter");
        filterRegistrationBean.setOrder(97);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
