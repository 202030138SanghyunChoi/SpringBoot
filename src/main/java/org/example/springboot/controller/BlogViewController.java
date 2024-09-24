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

// View 를 제어하는 BlogController
// 생성자 자동 주입 RequiredArgsConstructor Annotation
@Controller
@RequiredArgsConstructor
public class BlogViewController {
    private final BlogService blogService;

    // 글 목록 조회
    @GetMapping("/articles")
    public String articles(Model model) {
        // ?????????????????????????????????????????????????????????????????????????????????????????????????
        List<ArticleResponse> articles = blogService.findAll().stream().map(ArticleResponse::new).toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    // id 값으로 글 조회
    @GetMapping("/articles/{id}")
    public String article(@PathVariable Long id, Model model) {
        // model 로 article 변수 전송, 새 dto 객체에 blogService.findById 서비스 사용
        model.addAttribute("article", new ArticleViewResponse(blogService.findById(id)));
        return "article";
    }

    // 생성, 수정
    @GetMapping("new-article")
    // id 는 옵션 값
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if(id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", article);
        }
        return "newArticle";
    }
}
