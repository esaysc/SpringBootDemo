package com.esaysc.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: gtlbcf Email: gtlbcf@163.com
 * @version: 1.0
 * @Date: 2023/02/14/18:10
 * @FileName: SampleInterceptor
 * @Description:
 */
public class SampleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("进入拦截器了");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //
        System.out.println("controller 执行完了");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("我获取到了一个返回的结果："+response);
        System.out.println("请求结束了");
    }
}

