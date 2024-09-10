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
public class AddArticleRequest {
    private String title;
    private String content;

    // title이랑 content를 엔티티 객체에 저장해서 리턴 
    public Article toEntity() {
        return new Article(title, content);
    }
}
