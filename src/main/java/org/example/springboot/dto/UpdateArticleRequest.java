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
// 다른 종류의 필드를 사용하기 위해 새로운 dto 객체 ArticleResponse 생성
// Service 의 update 메서드에서 사용
public class UpdateArticleRequest {
    private String title;
    private String content;
}
