package com.esaysc.demo.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/10/16:18
 * @FileName: UserInfoDTO
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: UserInfoDTO
 * @author: ccs
 * @Date: 2022/12/10 16:18
 * @Version: 1.0
 */
@Data
public class UserInfoDTO {
    @TableId("userId")
    private Integer userId;
    private String nickname;
    private String username;
    private String email;
    private String gender;
    private Date birth;
    private String avatar;
    private String cover;
    private String friend;
    private String headline;
    private Integer articleId;
}
