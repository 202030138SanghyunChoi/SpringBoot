package org.example.springboot.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

// Article 엔티티 클래스
// lombok Get, Set 메서드 및 기본 생성자
// Parameter 가 없는 생성자
// 모든 Parameter 가 있는 생성자
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

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    // Builder 패턴 방식 사용
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article(String title, String content, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    // set 으로 직접 접근하는 것이 아닌 update 메서드 사용
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}