package org.example.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// Getter 와 Setter 메서드 자동 생성
@Getter
@Setter
@Component
// application.yml 에서 설정한 jwt 를 갖고 옴
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String issuer;
    private String secretKey;
}