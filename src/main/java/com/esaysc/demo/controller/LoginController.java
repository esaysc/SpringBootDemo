package com.esaysc.demo.controller;

import com.esaysc.demo.dto.RegisterDTO;
import com.esaysc.demo.dto.UserDTO;
import com.esaysc.demo.mapper.UserMapper;
import com.esaysc.demo.service.UserService;
import com.esaysc.demo.utils.JwtUtils;
import com.esaysc.demo.utils.Result;
import com.esaysc.demo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/09/11:40
 * @FileName: LoginController
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: LoginController
 * @author: ccs
 * @Date: 2022/12/9 11:40
 * @Version: 1.0
 */
@RestController
//@CrossOrigin // 接收所有访问 设置后可以跨域访问
public class LoginController {

    @Resource
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        boolean bool = userService.login(userDTO);
        System.out.println(bool);
        if(StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){
            return Result.error();
        } else if (bool) {
            String token = JwtUtils.generateToken(userDTO.getUsername());
            Integer userId = userMapper.getUserIdByUsername(username);
            return Result.ok().data("token",token).data("username",username).data("userId",userId);
        } else{
            return Result.error();
        }
    }

    @PostMapping("/login/register")
    public Result register(@RequestBody RegisterDTO registerDTO){
        registerDTO.setAvatar("http://43.136.179.5:8048/img/e4d12fbf4c8643a09dd46d7afe858c80cy.jpg");
        if(registerDTO.getUsername() == null || registerDTO.getEmail() == null || registerDTO.getPassword() == null){
            return Result.error().data("info","数据有误！");
        }
        else{
            // 判断是否已经注册过
            Map<String,Boolean> registerMap = userService.register(registerDTO);
            System.out.println(registerMap.get("username"));
            if(registerMap.get("username") && registerMap.get("email")){
                System.out.println("info"+"用户名已存在且该邮箱已注册！");
                return Result.error().data("info","用户名已存在且该邮箱已注册！");
            }else if(registerMap.get("username") || registerMap.get("email")){
                if (registerMap.get("username")) {
                    System.out.println("用户名已存在！");
                    return Result.error().data("info", "用户名已存在！");
                }else{
                    System.out.println("该邮箱已注册！");

                    return Result.error().data("info", "该邮箱已注册！");
                }
            }
        }
        // 验证通过注册账号
        userMapper.insertUser(registerDTO);
        return Result.ok();
    }

    @PostMapping("/logout") // 登出
    public Result logout(@RequestBody UserDTO userDTO) {
        return Result.ok();
    }

}
