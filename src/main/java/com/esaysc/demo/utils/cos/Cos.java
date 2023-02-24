package com.esaysc.demo.utils.cos;

import com.esaysc.demo.entity.TmpSecret;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.Headers;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.*;
import com.qcloud.cos.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: gtlbcf Email: gtlbcf@163.com
 * @version: 1.0
 * @Date: 2023/02/08/21:38
 * @FileName: Cos
 * @Description: 存储对象服务
 */

public class Cos {
    // 文件名加时间戳
    public String dateDirectory(String directory,String fileName) {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); // add S if you need milliseconds
        DateFormat date2 = new SimpleDateFormat("yyyyMMddHHmmss"); // add S if you need milliseconds
        String result2 = directory+"/"+date2.format(new Date())+"-"+fileName;
        for (int i = 0; i < 6; i++) {
            directory += "/" + date.format(new Date()).split("-")[i] ;
        }
        String result = (directory+"/"+fileName);
        return result2;
    }


    /*
     * @description: 上传图片
     * @author: esaysc
     * @date: 2023/2/9 16:00
     * @param: [directory, fileName, localFile]
     * @return: void
     */
    // 上传文章 图片
    public void uploadArticleImg(String directory,String fileName,File localFile) {
        directory = dateDirectory(directory,fileName);
        // 指定文件将要存放的存储桶
        String bucketName = "cy-1314976122";
        uploadFile(directory,localFile,bucketName);
    }
    @Test
    public void test() {
        // 指定要上传的文件
        File localFile = new File("D:\\user\\图片\\[薦]_101\\[薦]_X101\\[薦]_YingKyuu_X101_003.png");

        uploadAvatarImg("Avatar","ass.png",localFile);

    }
    // 上传用户 头像
    public void uploadAvatarImg(String directory,String fileName,File localFile) {
        directory = dateDirectory(directory,fileName);
        // 指定文件将要存放的存储桶
        String bucketName = "cy-1314976122";
//        uploadFile(directory,localFile,bucketName);
//        superUpload("Avatar/20230213192852-ssd.png",localFile,bucketName);
        getFileUrl("Avatar/20230213192852-ssd.png",localFile,bucketName);
        downloadFileUrl("Avatar/20230213192852-ass.png",localFile,bucketName);

    }


    // 上传单个文件
    public void uploadFile(String directory,File localFile,String bucketName) {
        COSClient cosClient = loadClient(directory,localFile,bucketName);
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为 folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = directory;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
    }
    public COSClient loadClient(String directory,File localFile,String bucketName){
        TmpSecret tmpSecret = new GetTmpSecret().getAvatarCredential(directory,bucketName);
        // 1 传入获取到的临时密钥 (tmpSecretId, tmpSecretKey, sessionToken)
        String tmpSecretId = tmpSecret.getTmpSecretId();
        String tmpSecretKey = tmpSecret.getTmpSecretKey();
        String sessionToken = tmpSecret.getSessionToken();
        BasicSessionCredentials cred = new BasicSessionCredentials(tmpSecretId, tmpSecretKey, sessionToken);
        // 2 设置 bucket 的地域
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分
        Region region = new Region("ap-chengdu"); //COS_REGION 参数：配置成存储桶 bucket 的实际地域，例如 ap-beijing，更多 COS 地域的简称请参见 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }
    /*
     * @description: 创建 COSClient 实例
     * @author: esaysc
     * @date: 2023/2/13 17:18
     * @param: [directory, localFile, bucketName]
     * @return: com.qcloud.cos.COSClient
     */
    COSClient createCOSClient(String directory,File localFile,String bucketName) {
        TmpSecret tmpSecret = new GetTmpSecret().getAvatarCredential(directory,bucketName);
        // 这里需要已经获取到临时密钥的结果。
        // 临时密钥的生成参见 https://cloud.tencent.com/document/product/436/14048#cos-sts-sdk
        String tmpSecretId = tmpSecret.getTmpSecretId();
        String tmpSecretKey = tmpSecret.getTmpSecretKey();
        String sessionToken = tmpSecret.getSessionToken();

        COSCredentials cred = new BasicSessionCredentials(tmpSecretId, tmpSecretKey, sessionToken);

        // ClientConfig 中包含了后续请求 COS 的客户端设置：
        ClientConfig clientConfig = new ClientConfig();

        // 设置 bucket 的地域
        // COS_REGION 请参见 https://cloud.tencent.com/document/product/436/6224
        clientConfig.setRegion(new Region("ap-chengdu"));

        // 设置请求协议, http 或者 https
        // 5.6.53 及更低的版本，建议设置使用 https 协议
        // 5.6.54 及更高版本，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);

        // 以下的设置，是可选的：

        // 设置 socket 读取超时，默认 30s
        clientConfig.setSocketTimeout(30*1000);
        // 设置建立连接超时，默认 30s
        clientConfig.setConnectionTimeout(30*1000);

        // 如果需要的话，设置 http 代理，ip 以及 port
//        clientConfig.setHttpProxyIp("httpProxyIp");
//        clientConfig.setHttpProxyPort(80);

        // 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }
    /*
     * @description: 创建TransferManager实例
     * @author: esaysc
     * @date: 2023/2/13 17:19
     * @param: [directory, localFile, bucketName]
     * @return: com.qcloud.cos.transfer.TransferManager
     */
    TransferManager createTransferManager(String directory,File localFile,String bucketName) {
        // 创建一个 COSClient 实例，这是访问 COS 服务的基础实例。
        // 详细代码参见本页: 简单操作 -> 创建 COSClient
        COSClient cosClient = createCOSClient(directory,localFile,bucketName);
//        COSClient cosClient = loadClient(directory,localFile,bucketName);
        // 自定义线程池大小，建议在客户端与 COS 网络充足（例如使用腾讯云的 CVM，同地域上传 COS）的情况下，设置成16或32即可，可较充分的利用网络资源
        // 对于使用公网传输且网络带宽质量不高的情况，建议减小该值，避免因网速过慢，造成请求超时。
        ExecutorService threadPool = Executors.newFixedThreadPool(32);

        // 传入一个 threadpool, 若不传入线程池，默认 TransferManager 中会生成一个单线程的线程池。
        TransferManager transferManager = new TransferManager(cosClient, threadPool);

        // 设置高级接口的配置项
        // 分块上传阈值和分块大小分别为 5MB 和 1MB
        TransferManagerConfiguration transferManagerConfiguration = new TransferManagerConfiguration();
        transferManagerConfiguration.setMultipartUploadThreshold(5*1024*1024);
        transferManagerConfiguration.setMinimumUploadPartSize(1*1024*1024);
        transferManager.setConfiguration(transferManagerConfiguration);

        return transferManager;
    }
    /*
     * @description: 销毁 TransferManager 实例
     * @author: esaysc
     * @date: 2023/2/13 17:17
     * @param: [transferManager]
     * @return: void
     */
    void shutdownTransferManager(TransferManager transferManager) {
        // 指定参数为 true, 则同时会关闭 transferManager 内部的 COSClient 实例。
        // 指定参数为 false, 则不会关闭 transferManager 内部的 COSClient 实例。
        transferManager.shutdownNow(true);
    }
    // 可以参考下面的例子，结合实际情况做调整
    void showTransferProgress(Transfer transfer) {
        // 这里的 Transfer 是异步上传结果 Upload 的父类
        System.out.println(transfer.getDescription());

        // transfer.isDone() 查询上传是否已经完成
        while (transfer.isDone() == false) {
            try {
                // 每 2 秒获取一次进度
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return;
            }

            TransferProgress progress = transfer.getProgress();
            long sofar = progress.getBytesTransferred();
            long total = progress.getTotalBytesToTransfer();
            double pct = progress.getPercentTransferred();
            System.out.printf("upload progress: [%d / %d] = %.02f%%\n", sofar, total, pct);
        }

        // 完成了 Completed，或者失败了 Failed
        System.out.println(transfer.getState());
    }
    public void superUpload(String directory,File localFile,String bucketName) {

        // 使用高级接口必须先保证本进程存在一个 TransferManager 实例，如果没有则创建
        // 详细代码参见本页：高级接口 -> 创建 TransferManager
        TransferManager transferManager = createTransferManager(directory,localFile,bucketName);

        // 对象键(Key)是对象在存储桶中的唯一标识。
        String key = directory;

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);

                try {
                    // 高级接口会返回一个异步结果Upload
                    // 可同步地调用 waitForUploadResult 方法等待上传完成，成功返回 UploadResult, 失败抛出异常
                    Upload upload = transferManager.upload(putObjectRequest);
                    // 打印上传进度，直到上传结束
                    showTransferProgress(upload);
                    UploadResult uploadResult = upload.waitForUploadResult();
                } catch (CosServiceException e) {
                    e.printStackTrace();
                } catch (CosClientException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        // 确定本进程不再使用 transferManager 实例之后，关闭之
        // 详细代码参见本页：高级接口 -> 关闭 TransferManager
                shutdownTransferManager(transferManager);

    }
    public void downloadFile(String directory,File localFile,String bucketName){
        TransferManager transferManager = createTransferManager(directory,localFile,bucketName);
        String key = directory;
        String localFilePath = "/path/to/localFile";
        File downloadFile = new File(localFilePath);
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        try {
            // 返回一个异步结果 Download, 可同步的调用 waitForCompletion 等待下载结束, 成功返回 void, 失败抛出异常
            Download download = transferManager.download(getObjectRequest, downloadFile);
            download.waitForCompletion();
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    // 确定本进程不再使用 transferManager 实例之后，关闭之
    // 详细代码参见本页：高级接口 -> 关闭 TransferManager
        shutdownTransferManager(transferManager);
    }












    public void getFileUrl(String directory,File localFile,String bucketName){
        COSClient cosClient = createCOSClient(directory,localFile,bucketName);
        String key = directory;

        // 设置签名过期时间(可选), 若未进行设置则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
        // 填写本次请求的参数，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的参数
        Map<String, String> params = new HashMap<String, String>();
//        params.put("param1", "value1");

        // 填写本次请求的头部，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的头部
        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("Content-Type", "image/png");

        // 请求的 HTTP 方法，上传请求用 PUT，下载请求用 GET，删除请求用 DELETE
        HttpMethodName method = HttpMethodName.GET;

        URL url = cosClient.generatePresignedUrl(bucketName, key, expirationDate, method, headers, params);
        System.out.println(url.toString());

        // 确认本进程不再使用 cosClient 实例之后，关闭之
        cosClient.shutdown();
    }
    public void downloadFileUrl(String directory,File localFile,String bucketName){
        // 调用 COS 接口之前必须保证本进程存在一个 COSClient 实例，如果没有则创建
// 详细代码参见本页：简单操作 -> 创建 COSClient

        COSClient cosClient = createCOSClient(directory,localFile,bucketName);

// 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
// 对象键(Key)是对象在存储桶中的唯一标识。详情请参见 [对象键](https://cloud.tencent.com/document/product/436/13324)
        String key = directory;

        GeneratePresignedUrlRequest req =
            new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);

// 设置下载时返回的 http 头
        ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides();
        String responseContentType = "image/x-icon";
        String responseContentLanguage = "zh-CN";
// 设置返回头部里包含文件名信息
        String responseContentDispositon = "filename=\"ass\"";
        String responseCacheControl = "no-cache";
        String cacheExpireStr =
            DateUtils.formatRFC822Date(new Date(System.currentTimeMillis() + 24L * 3600L * 1000L));
        responseHeaders.setContentType(responseContentType);
        responseHeaders.setContentLanguage(responseContentLanguage);
        responseHeaders.setContentDisposition(responseContentDispositon);
        responseHeaders.setCacheControl(responseCacheControl);
        responseHeaders.setExpires(cacheExpireStr);
        req.setResponseHeaders(responseHeaders);

// 设置签名过期时间(可选)，若未进行设置，则默认使用 ClientConfig 中的签名过期时间(1小时)
// 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);
        req.setExpiration(expirationDate);

// 填写本次请求的参数
//        req.addRequestParameter("param1", "value1");
// 填写本次请求的头部
// host 必填
        req.putCustomRequestHeader(Headers.HOST, cosClient.getClientConfig().getEndpointBuilder().buildGeneralApiEndpoint(bucketName));
        req.putCustomRequestHeader("Content-Type", "png");

        URL url = cosClient.generatePresignedUrl(req);
        System.out.println(url.toString());

// 确认本进程不再使用 cosClient 实例之后，关闭之
        cosClient.shutdown();
    }
}
