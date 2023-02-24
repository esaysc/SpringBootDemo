package com.esaysc.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/11/30/19:53
 * @FileName: HelloController
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: HelloController
 * @author: ccs
 * @Date: 2022/11/30 19:53
 * @Version: 1.0
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello 你好 world";
    }
    @RequestMapping(value = "/postTest1", method = RequestMethod.POST)
    public String testRequestMapping(String username, String password){
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        return "request 请求";

    }
}
