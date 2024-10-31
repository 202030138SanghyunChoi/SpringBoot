package org.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springboot.domain.Article;

import java.time.LocalDateTime;


// Arguments 가 없는 생성자 자동 생성
@NoArgsConstructor
// 모든 필드를 Arguments 로 하는 생성자 자동 생성
@AllArgsConstructor
// Getter 와 Setter 메서드 자동 생성
@Getter
@Setter
// Service 의 findAll 메서드에서 사용
public class ArticleViewResponse {
    // 아래 4가지 정보 반환
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    // View Controller 에서 게시글 단일 조회할때 사용
    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
