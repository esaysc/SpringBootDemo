package com.esaysc.demo.service;

import com.esaysc.demo.dto.RegisterDTO;
import com.esaysc.demo.dto.UserDTO;
import com.esaysc.demo.dto.UserInfoDTO;

import java.util.Map;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/04/16:18
 * @FileName: UserService
 * @Description: ${}
 */

public interface UserService {
    public boolean login(UserDTO userDTO);
    
    /*
     * @description: 验证用户是否注册
     * @author: esaysc
     * @date: 2022/12/10 13:15
     * @param: [username, email]
     * @return: void
     */
    Map<String, Boolean> register(RegisterDTO registerDTO);


    boolean validateNickname(UserInfoDTO userInfoDTO);
    boolean validateUsername(UserInfoDTO userInfoDTO);

}
