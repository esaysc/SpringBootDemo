package com.esaysc.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/03/0:08
 * @FileName: CyUser
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: CyUser
 * @author: ccs
 * @Date: 2022/12/3 0:08
 * @Version: 1.0
 */
@Data
public class CyUser {
    @TableId(type = IdType.AUTO)
//    @TableId(type = IdType.ASSIGN_UUID)
    private Integer userId;
    private String username;
    private String email;
    private String password;
    private String gender;

    private String nickname;
    public CyUser(Integer userId, String username, String email, String password, String gender) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
    public CyUser(String username, String email, String password, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public CyUser() {
    }

    @Override
    public String toString() {
        return "CyUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() { return nickname;
    }
}
