package org.example.springboot.repository;

import org.example.springboot.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 확장하므로 CRUD 기본 탑재
public interface ArticleRepository extends JpaRepository<Article, Long> {

}