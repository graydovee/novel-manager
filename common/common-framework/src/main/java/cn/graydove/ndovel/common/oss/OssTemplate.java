package cn.graydove.ndovel.common.oss;

import cn.graydove.ndovel.common.Singleton;
import cn.graydove.ndovel.common.properties.OssProperties;
import cn.graydove.ndovel.common.response.Response;
import cn.graydove.ndovel.common.utils.UploadUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlPath;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

/**
 * @author graydove
 */
@Slf4j
public class OssTemplate {

    private OssProperties ossProperties;

    private Singleton<OSS> ossSingleton = new Singleton<>();

    public OssTemplate(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    private OSS getOss() {
        return new OSSClientBuilder().build(
                ossProperties.getEndpoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret()
        );
    }

    /**
     * 上传文件，以IO流方式
     *
     * @param inputStream 输入流
     * @param objectName  唯一objectName（在oss中的文件名字）
     */
    public Response<String> upload(InputStream inputStream, String objectName) {
        try {
            // 创建OSSClient实例。
            OSS ossClient = getOss();
            // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
            ossClient.putObject(ossProperties.getBucketName(), objectName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            return Response.success(objectName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Response.fail();
    }

    /**
     * 上传文件，以url方式
     *
     * @param url 文件外部url
     * @param objectName  唯一objectName（在oss中的文件名字）
     */
    public Response<String> upload(String url, String objectName) {

        try {
            HttpResponse response = HttpUtil.createGet(url).execute();
            String contentType = response.header("Content-Type");
            byte[] bytes = response.bodyBytes();
            // 创建OSSClient实例。
            OSS ossClient = getOss();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);
            objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
            objectName = objectName + "." + UploadUtil.getImageSuffix(contentType);
            // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
            ossClient.putObject(ossProperties.getBucketName(), objectName, new ByteArrayInputStream(bytes), objectMetadata);
            // 关闭OSSClient。
            ossClient.shutdown();
            return Response.success(UrlBuilder.of(ossProperties.getBaseUrl(), Charset.defaultCharset()).setPath(UrlPath.of(objectName, Charset.defaultCharset())).build());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Response.fail();
    }

    /**
     * 删除OSS中的单个文件
     *
     * @param objectName 唯一objectName（在oss中的文件名字）
     */
    public void delete(String objectName) {
        try {
            // 创建OSSClient实例。
            OSS ossClient = getOss();
            // 删除文件。
            ossClient.deleteObject(ossProperties.getBucketName(), objectName);
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 批量删除OSS中的文件
     *
     * @param objectNames oss中文件名list
     */
    public void delete(List<String> objectNames) {
        try {
            // 创建OSSClient实例。
            OSS ossClient = getOss();
            // 批量删除文件。
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(ossProperties.getBucketName()).withKeys(objectNames));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 获取文件临时url
     *
     * @param objectName    oss中的文件名
     * @param effectiveTime 有效时间(ms)
     */
    public String getUrl(String objectName, long effectiveTime) {
        OSS ossClient = getOss();
        // 设置URL过期时间
        Date expiration = new Date(System.currentTimeMillis() + effectiveTime);
        GeneratePresignedUrlRequest generatePresignedUrlRequest;
        generatePresignedUrlRequest = new GeneratePresignedUrlRequest(ossProperties.getBucketName(), objectName);
        generatePresignedUrlRequest.setExpiration(expiration);
        URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

}
