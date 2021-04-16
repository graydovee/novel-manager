package cn.graydove.ndovel.common.properties;

import lombok.Data;

/**
 * @author graydove
 */
@Data
public class OssProperties {

    private Boolean enable;

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String baseUrl;
}
