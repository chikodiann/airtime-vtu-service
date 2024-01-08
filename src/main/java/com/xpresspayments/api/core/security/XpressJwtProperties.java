package com.xpresspayments.api.core.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.security.jwt")
@Data
public class XpressJwtProperties {
    private String secretKey;

    private long expiration;

    private long refreshTokenExpiration;
}
