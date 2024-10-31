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
// Service 의 save 서비스에서 사용
public class AddArticleRequest {
    // 필드 설정(title 이랑 content 는 생성자에서 넣어줄 거고, createAt 이랑 updateAt 은 현재 시각으로 설정)
    private String title;
    private String content;
    private LocalDateTime createAt = LocalDateTime.now();
    private LocalDateTime updateAt = LocalDateTime.now();

    // title 이랑 content 를 엔티티 객체에 저장해서 리턴하는 메서드
    // 저장 서비스에서 사용
    public Article toEntity() {
        return new Article(title, content, createAt, updateAt);
    }
}
