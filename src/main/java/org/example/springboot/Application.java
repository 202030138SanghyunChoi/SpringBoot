package org.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Spring Boot 애플리케이션이라고 지정하고, 기본 설정 동작
@SpringBootApplication
// CreatedDate 와 LastModifiedBy 를 사용하기 위해 EnableJpaAuditing Annotation 사용
@EnableJpaAuditing
// JWT Properties 를 설정하기 위해 EnableConfigurationProperties Annotation 설정 
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
