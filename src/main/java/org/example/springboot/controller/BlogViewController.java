package org.example.springboot.controller;

import java.util.List;

import org.example.springboot.domain.Article;
import org.example.springboot.dto.ArticleViewResponse;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.example.springboot.dto.ArticleResponse;
import org.example.springboot.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

// View 를 제어하는 BlogController. Http 상태코드가 아닌 templates 파일 이름 return
// 생성자 자동 주입 RequiredArgsConstructor Annotation
@Controller
@RequiredArgsConstructor
public class BlogViewController {
    private final BlogService blogService;

    // 글 목록 조회
    @GetMapping("/articles")
    public String articles(Model model) {
        // ResponseEntity 에 넣을 ArticleResponse 의 List 형태에 findAll() 서비스 동작
        // ArticleResponse::new 는 ArticleResponse 클래스의 생성자이다.
        // findAll 서비스 동작 후에 찾은 값들을 ArticleResponse 타입으로 변환하여 toList() 로 뿌려줌.
        List<ArticleResponse> articles = blogService.findAll().stream().map(ArticleResponse::new).toList();
        
        // view 로 전송
        model.addAttribute("articles", articles);

        // articleList.html 로 리턴
        return "articleList";
    }

    // id 값으로 글 조회
    @GetMapping("/articles/{id}")
    // URI 변수 id 를 사용하기 때문에 PathVariable Annotation 사용
    public String article(@PathVariable Long id, Model model) {
        // view 로 article 변수 전송, 새 dto 객체에 blogService.findById 서비스 사용
        model.addAttribute("article", new ArticleViewResponse(blogService.findById(id)));
        // article.html 리턴
        return "article";
    }

    // 생성, 수정
    @GetMapping("/new-article")
    // @RequestParam Annotation 에 required 를 false 로 설정하여 id Parameter 를 옵션 값으로 지정
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        // id 값이 없다면 생성 로직(new ArticleViewResponse())
        if(id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        // 아니라면 수정 로직 수행(경로 변수 id 값을 전달받아 article 객체 전달
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", article);
        }
        // newArticle.html 리턴
        return "newArticle";
    }
}
