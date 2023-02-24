package com.esaysc.demo.config;

import com.esaysc.demo.interceptor.SampleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: gtlbcf Email: gtlbcf@163.com
 * @version: 1.0
 * @Date: 2023/02/14/18:12
 * @FileName: InterceptorConfig
 * @Description:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public SampleInterceptor sampleInterceptor() {
        return new SampleInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sampleInterceptor()).addPathPatterns("/**").excludePathPatterns("/**/LoginController/**/","/**/error")
            .excludePathPatterns("/error");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
