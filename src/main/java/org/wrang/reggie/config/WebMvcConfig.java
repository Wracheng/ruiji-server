package org.wrang.reggie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.wrang.reggie.common.JacksonObjectMapper;

import java.util.List;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    static final String ORIGINS[] = new String[]{"GET", "POST", "PUT", "DELETE"};

    // 资源拦截
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods(ORIGINS).maxAge(3600);
    }

    /**
     * 扩展mvc框架的消息转换器
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        // 创建消息对象
        MappingJackson2HttpMessageConverter mj2HMC = new MappingJackson2HttpMessageConverter();
        // 设置消息对象用jackson将java对象转为json
        mj2HMC.setObjectMapper(new JacksonObjectMapper());
        // 将配置好的消息转换器实例追加到mvc框架的转换器集合中
        // 默认SpringMVC有8个转换器，我们自定义一个后有9个了
        converters.add(0, mj2HMC);
    }
}
