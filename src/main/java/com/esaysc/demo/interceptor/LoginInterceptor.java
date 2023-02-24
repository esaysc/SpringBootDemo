package com.esaysc.demo.interceptor;

import com.esaysc.demo.dto.UserInfoDTO;
import com.esaysc.demo.service.UserService;
import com.esaysc.demo.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/11/30/23:05
 * @FileName: LoginInterceptor
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: LoginInterceptor
 * @author: ccs
 * @Date: 2022/11/30 23:05
 * @Version: 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("进入login拦截器了");

        //1.获取所有请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        //2.遍历
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            // 判断 headerNames 中是否有 authorization
            if(name.equals("authorization")){
//                System.out.println("找到目标"+name);
                // 通过截取 得到token
                String header = request.getHeader(name);
                int startDotIndex = "Bearer".length()+1;
                String token = header.substring(startDotIndex);
                System.out.println("token:"+token);
                // 判断token 是否存在
                try {
                    // 解析token 获取用户名
                    String username = JwtUtils.getClaimsByToken(token).getSubject();
                    System.out.println("username:"+username);
                    UserInfoDTO userInfoDTO = new UserInfoDTO();
                    userInfoDTO.setUsername(username);
//                    Boolean result = userService.validateUsername(userInfoDTO);
//                    System.out.println("result"+result);
                }catch (Exception e){
                    return false;
                }
            }
            //根据名称获取请求头的值

        }
        System.out.println("发送请求的对象 :: "+handler);
        return true;
    }

}
