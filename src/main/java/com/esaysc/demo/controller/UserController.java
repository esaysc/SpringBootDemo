package com.esaysc.demo.controller;

import com.esaysc.demo.dto.UserInfoDTO;
import com.esaysc.demo.entity.CyUser;
import com.esaysc.demo.mapper.UserMapper;
import com.esaysc.demo.service.UserService;
import com.esaysc.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/02/23:58
 * @FileName: UserController
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: UserController
 * @author: ccs
 * @Date: 2022/12/2 23:58
 * @Version: 1.0
 */
    @RestController
    @RequestMapping("/user") // 请求总映射
//    @CrossOrigin // 接收所有访问 设置后可以跨域访问
public class UserController {
    @Resource
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/cyuser")
    public List<CyUser> query() {
        List<CyUser> user = userMapper.getUser();
        System.out.println(user);
        return user;
    }

    @PostMapping("/info") // 接收用户名返回用户的详细信息
    public UserInfoDTO info(@RequestBody UserInfoDTO userInfoDTO) {
        System.out.println("userInfoDTO"+userInfoDTO);
        UserInfoDTO info = userMapper.getUserInfo(userInfoDTO);
        return info;
    }
    @PostMapping("/info/userId") // 接收用户ID返回用户的详细信息
    public UserInfoDTO getUserInfoByUserId(@RequestBody UserInfoDTO userInfoDTO) {
        System.out.println("userInfoDTO"+userInfoDTO);
        UserInfoDTO info = userMapper.getUserInfoByUserId(userInfoDTO);
        return info;
    }
    @PostMapping("/info/articleId") // 接收文章ID返回用户(文章作者)的详细信息
    public UserInfoDTO getUserInfoByArticleId(@RequestBody UserInfoDTO userInfoDTO) {
        System.out.println("userInfoDTO"+userInfoDTO);
        UserInfoDTO info = userMapper.getUserInfoByArticleId(userInfoDTO);
        return info;
    }
    @PostMapping("/saveInfo") // 保存提交的用户信息
    public Result saveInfo(@RequestBody UserInfoDTO userInfoDTO) {
        boolean bool = userService.validateNickname(userInfoDTO);
        System.out.println("bool::::::"+bool);
        if(bool){
            return Result.error().data("info","昵称已占用");
        }
        System.out.println("userInfoDTO"+userInfoDTO);
        Integer success = userMapper.updateUserInfo(userInfoDTO);

        return Result.ok().data("info","修改成功");
    }

    @PostMapping("/logout") // "token.xxx"
    public Result logout() { return Result.ok(); }
}
