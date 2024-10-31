package org.example.springboot.repository;

import org.example.springboot.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 확장하므로써 CRUD 기본 탑재(Entity 객체 이름과 PK 필드의 타입)
public interface ArticleRepository extends JpaRepository<Article, Long> {

}