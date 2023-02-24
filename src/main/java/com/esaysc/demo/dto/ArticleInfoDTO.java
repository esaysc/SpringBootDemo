package com.esaysc.demo.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/12/18:41
 * @FileName: ArticleInfoDTO
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: ArticleInfoDTO
 * @author: ccs
 * @Date: 2022/12/12 18:41
 * @Version: 1.0
 */
    @Data
public class ArticleInfoDTO {
    @TableId("articleId")
    private Integer articleId;
    private Integer userId;
    private String title;
    private String contentMarkdown;
    private String followerCount;
    private Integer voteUpCount;
    private Integer voteDownCount;
    private Date createTime;
}
