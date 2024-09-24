package org.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springboot.domain.Article;

import java.time.LocalDateTime;

// 기본 생성자 및 모든 필드 생성자
// Getter 와 Setter 롬복
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Service 의 save 서비스에서 사용
public class AddArticleRequest {
    private String title;
    private String content;
    private LocalDateTime createdAt;

    // title 이랑 content 를 엔티티 객체에 저장해서 리턴하는 메서드
    public Article toEntity() {
        return new Article(title, content);
    }
}
