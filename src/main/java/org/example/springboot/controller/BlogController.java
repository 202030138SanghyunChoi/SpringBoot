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

// JSON 을 다루는 RestController Annotation(Controller + ResponseBody)
// RequiredArgsConstructor Annotation 이 생성된 필드의 생성자를 자동으로 생성
@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    // TEST Mapping
    @GetMapping("/api/test")
    public ResponseEntity<String> test() {
        // ResponseEntity(body 내용, 상태 OK(200)) 리턴(Http 응답 클래스. JSON 이나 XML 형식으로 응답 반환)
        return new ResponseEntity<>("test Word", HttpStatus.OK);
    }

    // POST Mapping(CREATE)
    @PostMapping("/api/articles")
    // RequestBody: json -> dto 매핑(AddArticleRequest 가 dto)
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest addArticleRequest) {
        // Article 클래스 객체에 addArticleRequest dto 를 넣은 blogService 의 저장 서비스 리턴.
        Article article = blogService.save(addArticleRequest);

        // article 클래스 객체 반환 응답코드 201(Create)
        return ResponseEntity.status(201).body(article);
    }

    // GET Mapping(전체 SELECT)
    @GetMapping("/api/articles")
    // ArticleResponse 를 List 로 담은 ResponseEntity 반환
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        // ResponseEntity 에 넣을 ArticleResponse 의 List 형태에 findAll() 서비스 동작
        // findAll 서비스 동작 후에 찾은 값들을 ArticleResponse 타입으로 변환하여 toList() 로 뿌려줌.
        List<ArticleResponse> articleResponseList = blogService.findAll().stream().map(ArticleResponse::new).toList();
        // 상태 200(OK)
        return ResponseEntity.status(200).body(articleResponseList);
    }

    // GET 매핑(SELECT)
    // {id} 로 URI 변수 사용 @PathVariable 을 통해 전달 받음
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        // findById 서비스 수행
        Article article = blogService.findById(id);
        // 상태 OK(200)
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    // Delete 매핑(DELETE)
    // {id} 로 URI 변수 사용 @PathVariable 을 통해 전달 받음
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        // delete 서비스 수행
        blogService.delete(id);
        // 상태 OK(200)
        return ResponseEntity.ok().build();
    }

    // PUT 매핑(UPDATE)
    // {id} 로 URI 변수 사용 @PathVariable 을 통해 전달 받음
    @PutMapping("/api/articles/{id}")
    // id URI 를 위한 PathVariable 과 업데이트 하기 위한 RequestBody
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);
        // 상태 OK(200)
        return ResponseEntity.ok().body(updateArticle);
    }
}