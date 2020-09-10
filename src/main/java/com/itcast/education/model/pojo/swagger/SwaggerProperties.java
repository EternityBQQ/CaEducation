package com.itcast.education.model.pojo.swagger;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zheng.zhang
 * Description Swagger 配置文件
 * @date 2020/9/10 15:38
 * Version 1.0
 */
@Data
@Component
@PropertySource(value = "classpath:config/swagger.yml", ignoreResourceNotFound = true, encoding = "utf-8")
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    @Value("${title}")
    private String title;
    @Value("${desc}")
    private String desc;
    @Value("${objectVersion}")
    private String objectVersion;
    @Value("${license}")
    private String license;
    @Value("${licenseUrl}")
    private String licenseUrl;
    @Value("${basePackage}")
    private String basePackage;
    @Value("${groupName}")
    private String groupName;
    @Value("${contactName}")
    private String contactName;
    @Value("${contactUrl}")
    private String contactUrl;
    @Value("${contactEmail}")
    private String contactEmail;
    @Value("${termsOfServiceUrl}")
    private String termsOfServiceUrl;
}
