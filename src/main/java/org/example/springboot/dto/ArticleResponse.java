package org.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springboot.domain.Article;

// Arguments 가 없는 생성자 자동 생성
@NoArgsConstructor
// 모든 필드를 Arguments 로 하는 생성자 자동 생성
@AllArgsConstructor
// Getter / Setter 메서드 자동 생성
@Getter
@Setter
// Article 출력 시 사용할 dto
// Service 의 findAll 메서드에서 사용
public class ArticleResponse {
    // 아래 3가지 정보 반환
    private Long id;
    private String title;
    private String content;

    // API Controller 에서 조회,전체 조회랑 View 용 Controller 에서 전체 조회할 때 사용
    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
