package com.esaysc.demo.controller;

import com.esaysc.demo.dto.CosFileDTO;
import com.esaysc.demo.entity.TmpSecret;
import com.esaysc.demo.utils.cos.Cos;
import com.esaysc.demo.utils.cos.CosResult;
import com.esaysc.demo.utils.cos.GetTmpSecret;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: gtlbcf Email: gtlbcf@163.com
 * @version: 1.0
 * @Date: 2023/02/14/18:01
 * @FileName: CosController
 * @Description:
 */
@RestController
@RequestMapping("/cos") // 请求总映射
@CrossOrigin // 接收所有访问 设置后可以跨域访问
public class CosController {
    @PostMapping("/secret")
    public CosResult getTmpSecret(@RequestBody CosFileDTO cosFileDTO) {

        System.out.println(cosFileDTO.getDirectory()+"-"+cosFileDTO.getBucketName());
        Cos cos = new Cos();
//        String directory = cos.dateDirectory(cosFileDTO.getDirectory(),cosFileDTO.getFilename());
        String directory = cosFileDTO.getDirectory();
        TmpSecret temSecret = new GetTmpSecret().getAvatarCredential(directory,cosFileDTO.getBucketName());
        CosResult cosResult = new CosResult();
        long startTime = System.currentTimeMillis()/1000;
        long expiredTime = startTime+1800;

        return cosResult.credentials(temSecret,startTime,expiredTime).data("directory",directory);
    }
}
