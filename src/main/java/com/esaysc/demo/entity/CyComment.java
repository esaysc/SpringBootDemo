package com.esaysc.demo.entity;

import java.sql.Date;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/04/10:20
 * @FileName: CyComment
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: CyComment
 * @author: ccs
 * @Date: 2022/12/4 10:20
 * @Version: 1.0
 */
public class CyComment {
    private int commentId;
    private int commentableId;
    private String commentType;
    private int userId;
    private String content;
    private int replyCount;
    private int voteCount;
    private int voteUpCount;
    private int voteDownCount;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
}
