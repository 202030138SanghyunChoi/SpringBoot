package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.Article;
import org.example.springboot.dto.AddArticleRequest;
import org.example.springboot.repository.ArticleRepository;
import org.springframework.stereotype.Service;

// 서비스 어노테이션
// RequiredArgsConstructor??????????????
@Service
@RequiredArgsConstructor
public class BlogService {

    private final ArticleRepository articleRepository;

    public Article save(AddArticleRequest request) {
        // 리포지토리 동작
        return articleRepository.save(request.toEntity());
    }
}
