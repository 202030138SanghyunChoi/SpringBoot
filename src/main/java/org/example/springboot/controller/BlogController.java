package org.example.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.Article;
import org.example.springboot.dto.AddArticleRequest;
import org.example.springboot.dto.ArticleResponse;
import org.example.springboot.dto.UpdateArticleRequest;
import org.example.springboot.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// json을 다루는 RestController 어노테이션(Controller + ResponseBody)
// RequiredArgsConstructor??????????????
@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    // TEST GET 매핑
    @GetMapping("/api/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("test Word", HttpStatus.OK);
    }

    // CREATE POST 매핑
    @PostMapping("/api/articles")
    // RequestBody: json -> dto 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest addArticleRequest) {
        // 서비스 동작.
        Article article = blogService.save(addArticleRequest);

        // 응답 처리. 응답코드 201(Create)
        return ResponseEntity.status(201).body(article);
    }

    // SELECT ALL GET 매핑
    // ?????????????????????
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articleResponseList = blogService.findAll().stream().map(ArticleResponse::new).toList();
        return ResponseEntity.ok().body(articleResponseList);
    }

    // SELECT GET 매핑
    // ????????????????????????
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    // Delete 매핑
    // ????????????????????????
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Update 매핑
    // ????????????????????????
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);
        return ResponseEntity.ok().body(updateArticle);
    }
}