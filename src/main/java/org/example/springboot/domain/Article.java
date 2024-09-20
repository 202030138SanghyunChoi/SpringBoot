package org.example.springboot.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Article 엔티티 클래스
// lombok Get, Set 메서드 및 기본 생성자
@Entity
@Getter
@NoArgsConstructor
public class Article {
    // PK 지정
    // 데이터베이스 자동생성 기능 따르기
    // 칼럼 속성 지정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}