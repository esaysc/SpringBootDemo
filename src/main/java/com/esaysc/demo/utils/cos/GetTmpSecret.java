package com.esaysc.demo.utils.cos;

import com.esaysc.demo.entity.TmpSecret;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import org.springframework.jdbc.support.JdbcUtils;

import java.io.InputStream;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @Author: gtlbcf Email: gtlbcf@163.com
 * @version: 1.0
 * @Date: 2023/02/08/20:41
 * @FileName: GetTmpSecret
 * @Description:
 */
public class GetTmpSecret {
    /**
     * 基本的临时密钥申请示例，适合对一个桶内的一批对象路径，统一授予一批操作权限
     */


    // 获取文章上传图片的临时 凭据
    public TmpSecret getArticleImgCredential(String directoryName,String bucketName) {
        return getTmpSecret(directoryName,bucketName);
    }

    // 获取头像上传图片的临时 凭据
    public TmpSecret getAvatarCredential(String directoryName,String bucketName) {
        return getTmpSecret(directoryName,bucketName);
    }

    private TmpSecret getTmpSecret(String directoryName,String bucketName) {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {
            Properties properties = new Properties();
//            File configFile = new File("local.properties");
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("local.properties");
            properties.load(inputStream);

            // 云 api 密钥 SecretId
            config.put("secretId", properties.getProperty("secretIdImg"));
            // 云 api 密钥 SecretKey
            config.put("secretKey", properties.getProperty("secretKeyImg"));

            if (properties.containsKey("https.proxyHost")) {
                System.setProperty("https.proxyHost", properties.getProperty("https.proxyHost"));
                System.setProperty("https.proxyPort", properties.getProperty("https.proxyPort"));
            }

            // 设置域名,可通过此方式设置内网域名
            //config.put("host", "sts.internal.tencentcloudapi.com");

            // 临时密钥有效时长，单位是秒
            config.put("durationSeconds", 1800);

            // 换成你的 bucket
            config.put("bucket", bucketName);
            // 换成 bucket 所在地区
            config.put("region", "ap-chengdu");

            // 可以通过 allowPrefixes 指定前缀数组, 例子： a.jpg 或者 a/* 或者 * (使用通配符*存在重大安全风险, 请谨慎评估使用)
            config.put("allowPrefixes", new String[] {
                directoryName,
            });

            // 密钥的权限列表。简单上传和分片需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[] {
                // 简单上传
                "name/cos:PutObject",
                "name/cos:PostObject",
                // 分片上传
                "name/cos:InitiateMultipartUpload",
                "name/cos:ListMultipartUploads",
                "name/cos:ListParts",
                "name/cos:UploadPart",
                "name/cos:CompleteMultipartUpload",
                // 允许下载 访问
                "name/cos:GetObject"
            };
            config.put("allowActions", allowActions);

            Response response = CosStsClient.getCredential(config);

            TmpSecret tmpSecret = new TmpSecret();
            tmpSecret.setTmpSecretId(response.credentials.tmpSecretId);
            tmpSecret.setTmpSecretKey(response.credentials.tmpSecretKey);
            tmpSecret.setSessionToken(response.credentials.sessionToken);
            return tmpSecret;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("no valid secret !");
        }
    }

}
