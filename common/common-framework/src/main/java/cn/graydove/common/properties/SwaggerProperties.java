package cn.graydove.common.properties;

import lombok.Data;

/**
 * @author graydove
 */
@Data
public class SwaggerProperties {

    Boolean enable = true;

    String pathMapping;

    String basePackage;

    String title;

    String description;

    String contactName = "graydove";

    String contactEmail = "graydovee@foxmail.com";

    String contactUrl = "https://blog.graydove.cn/";
}
