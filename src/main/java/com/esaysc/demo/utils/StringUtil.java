package com.esaysc.demo.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lbcf Email: lbcf@126.com
 * @version:
 * @Date: 2022/11/05/14:57
 * @Description:
 */
public class StringUtil {
    // 判断字符串是否为空
    public static boolean isEmpty(String str) {
        return str==null || "".equals(str);
    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

}
