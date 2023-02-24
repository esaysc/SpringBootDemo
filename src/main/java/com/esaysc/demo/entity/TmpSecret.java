package com.esaysc.demo.entity;

import lombok.Data;

/**
 * @Author: gtlbcf Email: gtlbcf@163.com
 * @version: 1.0
 * @Date: 2023/02/08/21:25
 * @FileName: TmpSecret
 * @Description:
 */
@Data
public class TmpSecret {
    private String tmpSecretId;
    private String tmpSecretKey;
    private String sessionToken;

}
