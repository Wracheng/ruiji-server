package org.wrang.reggie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.wrang.reggie.Interceptor.LoginInterceptor;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 * 拦截器装配
 */
@Slf4j
@Configuration
public class myInterceptors implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // PathPatterns 是请求前缀，需要排除静态资源，使之不被拦截，当然也可用统一前缀的方式方便排除，可以在配置文件配置spring.mvc.static-path-pattern=/static/**，然后排除/static/**这一个就行了
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/css/**","/fonts/**","/images/**","/js/**","/employee/login");
    }

}
