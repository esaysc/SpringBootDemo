package com.esaysc.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esaysc.demo.dto.RegisterDTO;
import com.esaysc.demo.dto.UserDTO;
import com.esaysc.demo.dto.UserInfoDTO;
import com.esaysc.demo.entity.CyUser;
import com.esaysc.demo.mapper.CyUserMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/04/16:18
 * @FileName: UserServiceImpl
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: UserServiceImpl
 * @author: ccs
 * @Date: 2022/12/4 16:18
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<CyUserMapper, CyUser> implements UserService{
    private QueryWrapper<CyUser> queryWrapper = new QueryWrapper<>();

    @Override
    public boolean login(UserDTO userDTO) {
        queryWrapper.clear();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        // 查询满足条件的对象类实体
        CyUser one = getOne(queryWrapper);
        // 如果有一条记录则代表 该用户存在
        // 返回真 代表该用户存在
        return one != null;
    }
    @Override
    public Map<String, Boolean> register(RegisterDTO registerDTO) {
        Map<String, Boolean> registerMap = new HashMap<String,Boolean>();
        if(registerDTO.getUsername() != null){
            queryWrapper.clear();
            // 查询用户名是否存在
            queryWrapper.eq("username", registerDTO.getUsername());
            CyUser one = getOne(queryWrapper);
            registerMap.put("username",one != null);
        }
        if(registerDTO.getEmail() != null){
            queryWrapper.clear();
            // 查询用户邮箱是否注册
            queryWrapper.eq("email", registerDTO.getEmail());
            CyUser one = getOne(queryWrapper);
            registerMap.put("email",one != null);
        }
        return registerMap;
    }

    @Override
    public boolean validateUsername(UserInfoDTO userInfoDTO) {
        queryWrapper.clear();
        // 查询用户昵称是否被占用
        queryWrapper.eq("username", userInfoDTO.getUsername());
        CyUser one = getOne(queryWrapper);
        System.out.println("one.getUsername::::::"+one.getUsername());
        System.out.println("userInfoDTO.getUsername::::::"+userInfoDTO.getUsername());
        System.out.println("one::::::"+one);
        return one != null;
    }

    @Override
    public boolean validateNickname(UserInfoDTO userInfoDTO) {
        queryWrapper.clear();
        // 查询用户昵称是否被占用
        queryWrapper.eq("nickname", userInfoDTO.getNickname());
        CyUser one = getOne(queryWrapper);
//        if(one != null){
//            return true;
//        }else{
//            return false;
//        }
        return one!=null;
    }


}
