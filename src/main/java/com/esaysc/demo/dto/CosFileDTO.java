package com.esaysc.demo.dto;

import lombok.Data;

/**
 * @Author: gtlbcf Email: gtlbcf@163.com
 * @version: 1.0
 * @Date: 2023/02/23/22:12
 * @FileName: CosFileDTO
 * @Description:
 */
@Data
public class CosFileDTO {
    private String directory;
    private String filename;
    private String bucketName;
}
