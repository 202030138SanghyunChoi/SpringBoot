package org.example.springboot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.Article;
import org.example.springboot.dto.AddArticleRequest;
import org.example.springboot.dto.UpdateArticleRequest;
import org.example.springboot.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service Annotation 지정
// RequiredArgsConstructor Annotation 이 생성된 필드의 생성자를 자동으로 생성
@Service
@RequiredArgsConstructor
public class BlogService {
    
    private final ArticleRepository articleRepository;

    // 저장 서비스
    public Article save(AddArticleRequest request) {
        // JpaRepository 의 save 메서드 사용. AddArticleRequest 의 toEntity 메서드를 사용하여 title, content 를 갖고 있는 Article 객체 사용
        // 저장된 객체 반환.
        return articleRepository.save(request.toEntity());
    }

    // 전체 글 조회 서비스
    public List<Article> findAll() {
        // JpaRepository 의 findAll 메서드 사용. 찾은 내용들 반환.
        return articleRepository.findAll();
    }

    // 글 조회 서비스
    public Article findById(long id) {
        // JpaRepository 의 findById 메서드 사용
        // orElseThrow 로 예외 상황 처리(if문 역할)
        // 화살표 함수로 잘못된 Parameter(id) 가 넘어갔을 때 오류 출력
        return articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : "+ id));
    }

    // 삭제 서비스
    public void delete(long id) {
        // JpaRepository 의 deleteById 메서드 사용
        articleRepository.deleteById(id);
    }

    // 수정 서비스
    // DB 수정 시 트랜잭션 필요. 트랜잭션 설정 Annotation
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        // JpaRepository 의 findById 메서드 사용
        // orElseThrow 로 예외 상황 처리(if문 역할)
        // 화살표 함수로 잘못된 Parameter(id) 가 넘어갔을 때 오류 출력
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found :"+ id));
        // JpaRepository 의 update 메서드 사용
        article.update(request.getTitle(), request.getContent());
        // 수정된 사항(article 객체)을 반환
        return article;
    }
}
