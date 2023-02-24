package com.esaysc.demo.config;

import com.esaysc.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/11/30/23:08
 * @FileName: WebConfig
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: WebConfig
 * @author: ccs
 * @Date: 2022/11/30 23:08
 * @Version: 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addInterceptors(InterceptorRegistry registry){
//            拦截路径为user下的所有请求
            registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");
            registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/article/**");

//            registry.addInterceptor(new LoginInterceptor());

        }
}
