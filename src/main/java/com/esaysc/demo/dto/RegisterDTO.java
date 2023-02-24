package com.esaysc.demo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/09/22:14
 * @FileName: RegisterDTO
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: RegisterDTO
 * @author: ccs
 * @Date: 2022/12/9 22:14
 * @Version: 1.0
 */
@Data
public class RegisterDTO {
    private Integer userId;
    private String email;
    private String avatar;
    private String username;
    private String gender;
    private String password;
    private String checkPass;
    private Date createTime;
}
