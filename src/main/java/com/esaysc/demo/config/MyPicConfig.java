package com.esaysc.demo.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/11/23:24
 * @FileName: MyPicConfig
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: MyPicConfig
 * @author: ccs
 * @Date: 2022/12/11 23:24
 * @Version: 1.0
 */
@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler 客户端请求的路径url
        // addResourceLocations 图片存放的真实路径
        // registry.addResourceHandler("/upload/**").addResourceLocations("file:/opt/upload/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/upload/");

    }

}

