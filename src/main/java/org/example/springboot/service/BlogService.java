package org.example.springboot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.Article;
import org.example.springboot.dto.AddArticleRequest;
import org.example.springboot.dto.UpdateArticleRequest;
import org.example.springboot.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// 서비스 어노테이션
// RequiredArgsConstructor??????????????
@Service
@RequiredArgsConstructor
public class BlogService {

    private final ArticleRepository articleRepository;

    // 저장 서비스
    public Article save(AddArticleRequest request) {
        // 리포지토리 동작
        return articleRepository.save(request.toEntity());
    }

    // 전체 글 조회 서비스
    // ?????????????????????????????
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    // 글 조회 서비스
    // ?????????????????????????????
    public Article findById(long id) {
        return articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : "+ id));
    }

    // 삭제 서비스
    public void delete(long id) {
        articleRepository.deleteById(id);
    }

    // 수정 서비스
    // 트랜잭션 수행 Annotation
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found :"+ id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
