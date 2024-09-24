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
// 다른 종류의 필드를 사용하기 위해 새로운 dto 객체 ArticleResponse 생성
// Service 의 findAll 메서드에서 사용
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    // 컨트롤러의 1개 SELECT 에서 사용
    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
