package com.mbouhda.reddit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt.token")
@Data
public class ConfigProperties {

    private String secret;
    private Long validity;
}
