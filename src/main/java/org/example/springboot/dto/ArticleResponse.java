package org.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springboot.domain.Article;

// 기본 생성자 및 모든 필드 생성자
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

// 이게 왜 필요하지??????????????????????
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;

    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}