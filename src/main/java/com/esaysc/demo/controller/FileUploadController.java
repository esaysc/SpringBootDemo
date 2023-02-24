package com.esaysc.demo.controller;

import com.esaysc.demo.dto.UserInfoDTO;
import com.esaysc.demo.mapper.UserMapper;
import com.esaysc.demo.utils.RandomName;
import com.esaysc.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/11/30/22:05
 * @FileName: FileUploadController
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: FileUploadController
 * @author: ccs
 * @Date: 2022/11/30 22:05
 * @Version: 1.0
 */
    @RestController
public class FileUploadController {
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/upload")
    public Result up(Integer userId,MultipartFile photo, HttpServletRequest request) throws IOException {
        System.out.println("上传用户："+ userId);
        // 获取图片原始名称
        System.out.println(photo.getOriginalFilename());
        // 获取文件类型
        System.out.println(photo.getContentType());
        //System.out.println(System.getProperty("user.dir"));

        String path = request.getServletContext().getRealPath("/upload/");
        System.out.println(path);
        String avatarUrl = saveFile(photo,path);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setAvatar(avatarUrl);
        userInfoDTO.setUserId(userId);
        userMapper.updateUserAvatar(userInfoDTO);

        return Result.ok().data("avatarUrl",avatarUrl);

    }
    public String saveFile(MultipartFile photo, String path) throws IOException {
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
        RandomName randomName = new RandomName();
        String photoName = randomName.getRandomName(photo.getOriginalFilename());
        System.out.println("photoName："+photoName);
        // 返回客户端 文件地址 URL
        //String url = "http://43.136.179.5:8049/"+"/upload/" + photoName;
        // windows本地路径：---------------
//        File file = new File("R:/image/" + photoName);
        // 服务器本地路径：--------------
        File file = new File("/www/wwwroot/image/" + photoName);
//        String url = "localhost:8049"+"/upload/" + photoName;

        String url = "http://43.136.179.5:8048"+"/img/" + photoName;
        // 临时路径
        // File data = new File(path+photoName);

        photo.transferTo(file);
        return url;
    }
}
