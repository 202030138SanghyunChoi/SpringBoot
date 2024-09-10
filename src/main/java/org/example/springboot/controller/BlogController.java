package org.example.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.Article;
import org.example.springboot.dto.AddArticleRequest;
import org.example.springboot.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// json을 다루는 RestController 어노테이션(Controller + ResponseBody)
// RequiredArgsConstructor??????????????
@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    // SELECT GET 매핑
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
}