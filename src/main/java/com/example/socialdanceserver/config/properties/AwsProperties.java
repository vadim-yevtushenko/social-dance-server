package com.example.socialdanceserver.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "s3")
@Getter
@Setter
public class AwsProperties {

    private String key;

    private String secretKey;

    private String bucketName;

}
