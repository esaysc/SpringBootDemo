package com.esaysc.demo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/04/12:47
 * @FileName: Result
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: Result
 * @author: ccs
 * @Date: 2022/12/4 12:47
 * @Version: 1.0
 */
    // 统一返回结果的类
public class Result {
    private Boolean success;
    public String getMessage() {
        return message;
    }
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();
    public void setMessage(String message) {
        this.message = message;
    }
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }


    public Boolean getSuccess() { return success; }
    public void setSuccess(Boolean success) { this.success = success; }
    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code;}
    // 把构造方法私有
    public Result() {
    }
    // 成功静态方法
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }
    public static Result none() {
        Result r = new Result();
        return r;
    }
    // 失败静态方法
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }
    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
