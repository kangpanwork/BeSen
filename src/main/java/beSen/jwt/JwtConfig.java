package beSen.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 康盼Java开发工程师
 */
@Configuration
public class JwtConfig implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(JwtConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("jwt check...");
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/login");
    }
}
