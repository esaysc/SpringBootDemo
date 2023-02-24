package com.esaysc.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/03/12:20
 * @FileName: CyArticle
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: CyArticle
 * @author: ccs
 * @Date: 2022/12/3 12:20
 * @Version: 1.0
 */
    @Data
public class CyArticle {
    //@TableId(type = IdType.ASSIGN_UUID )
    @TableId(type = IdType.AUTO)
    private int articleId;
    private int userId;
    private String title;
    private String contentMarkdown;
    private Date createTime;


}
