package org.example.springboot.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

// Article 엔티티 클래스
@Entity
// lombok Get, Set 메서드 및 기본 생성자
@Getter
// Parameter 가 없는 생성자 자동 생성
@NoArgsConstructor
public class Article {
    // PK 지정
    @Id
    // 데이터베이스 자동생성 기능 따르기
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 칼럼 속성 지정(이름, null 되면 안됨, 수정 불가능)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // 칼럼 속성 지정(이름, null 되면 안됨, 수정 불가능)
    @Column(name = "title", nullable = false)
    private String title;

    // 칼럼 속성 지정(이름, null 되면 안됨, 수정 불가능)
    @Column(name = "content", nullable = false)
    private String content;
    
    // 엔티티 생성 시 날짜 시간정보 자동 생성
    @CreatedDate
    // 칼럼 속성 지정(이름, null 되면 안됨, 수정 불가능)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 엔티티 수정 시 날짜, 시간정보를 저장
    @LastModifiedBy
    // 칼럼 속성 지정(이름, null 되면 안됨, 수정 불가능)
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    // Builder 패턴 방식 사용(생성자처럼 쓰는 건데 훨씬 편함)
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // id 만 없고 Parameter 다 때려박은 생성자
    public Article(String title, String content, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    // 값을 변경할 때는 set 으로 직접 접근하는 것이 아닌 update 메서드 사용을 지향
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}