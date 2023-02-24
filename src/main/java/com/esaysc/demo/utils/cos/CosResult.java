package com.esaysc.demo.utils.cos;

import com.esaysc.demo.entity.TmpSecret;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gtlbcf Email: gtlbcf@163.com
 * @version: 1.0
 * @Date: 2023/02/23/22:27
 * @FileName: CosResult
 * @Description:
 */
public class CosResult {
    private TmpSecret credentials;
    public TmpSecret getCredentials() { return credentials;}
    public void setCredentials(TmpSecret credentials) { this.credentials = credentials;}

    private long startTime;
    public long getStartTime() { return startTime;}
    public void setStartTime(long startTime) { this.startTime = startTime;}

    private long expiredTime;
    public long getExpiredTime() { return expiredTime;}
    public void setExpiredTime(long expiredTime) { this.expiredTime = expiredTime;}
    private Map<String, Object> data = new HashMap<>();
    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    public CosResult() {
    }
    public static CosResult credentials(TmpSecret credentials,long startTime,long expiredTime) {
        CosResult c = new CosResult();
        c.setCredentials(credentials);
        c.setStartTime(startTime);
        c.setExpiredTime(expiredTime);
        return c;
    }

    public CosResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
