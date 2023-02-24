package com.esaysc.demo.utils;
import java.util.UUID;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/11/22:59
 * @FileName: RandomName
 * @Description: ${}
 */
/*
 * @description: 根据uuid+名称 生成随机文件名
 * @ClassName: RandomName
 * @author: ccs
 * @Date: 2022/12/11 22:59
 * @Version: 1.0
 */

public class RandomName {

    public static String getRandomName(String fileName){
        int index=fileName.lastIndexOf(".");
        String suffix=fileName.substring(index);//获取后缀名
        String uuidFileName;
        if(index>3){
            uuidFileName = UUID.randomUUID().toString().replace("-", "") +"cy"+suffix;
        }else{
            uuidFileName = UUID.randomUUID().toString().replace("-", "") +"sy"+suffix;
        }
        return uuidFileName;

    }

    public static void main(String[] args) {
        System.out.println(getRandomName("aaa.jpg"));
    }
}
