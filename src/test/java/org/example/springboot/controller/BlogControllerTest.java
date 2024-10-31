package org.example.springboot.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springboot.domain.Article;
import org.example.springboot.dto.AddArticleRequest;
import org.example.springboot.dto.UpdateArticleRequest;
import org.example.springboot.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Spring Boot Test Annotation
@SpringBootTest
// MockMVC 를 생성하고 자동으로 구성.(MockMVC - 서버 배포를 하지 않고 테스트 환경 제공)
@AutoConfigureMockMvc
class BlogControllerTest {
    // MockMvc 객체 사용
    @Autowired
    protected MockMvc mockMvc;

    // JSON 변환 시 사용
    @Autowired
    protected ObjectMapper objectMapper;

    // MockMvc 기본 설정 최상단 context 객체
    @Autowired
    private WebApplicationContext webApplicationContext;

    // 리포지토리 사용(JpaRepository extends)
    @Autowired
    private ArticleRepository articleRepository;
    
    // test 종료 시 마다 수행
    // MockMvc 커스텀
    @BeforeEach
    public void mockMvcSetup() {
        // 스프링 웹앱 컨텍스트를 로드하여 실제 환경과 유사한 환경에서 테스트 수행
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // 테스트 해놓은 것들 전부 삭제
        articleRepository.deleteAll();
    }

    // POST Mapping(CREATE) TEST
    @DisplayName("addArticle: POST MAPPING(CREATE)")
    // Test 최소 단위 설정
    @Test
    public void addArticle() throws Exception {
        // given(테스트 실행 준비)
        // 테스트에 사용되는 것들 설정
        final String url = "/api/articles";
        final String title = "testTitle";
        final String content = "testContent";
        final LocalDateTime createAt = LocalDateTime.now();
        final LocalDateTime updateAt = LocalDateTime.now();
        final AddArticleRequest request = new AddArticleRequest(title, content, createAt, updateAt);

        // objectMapper 를 사용하여 writeValueAsString() 메서드로 직렬화(JSON 으로 변환)하여 content 나 type 의 값을 전송하게 함
        final String requestBody = objectMapper.writeValueAsString(request);

        // when(테스트 실행)
        // perform() 메서드로 요청 전송(ResultActions 객체 반환).
        // 직렬화된 requestBody 를 JSON VALUE 타입으로 perform() 메서드를 사용하여 요청 전송
        ResultActions result = mockMvc.perform( post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody) );

        // then(테스트 결과 검증)
        // Http 응답코드가 Create(201)인지 확인
        result.andExpect(status().isCreated());

        // Article 전부 가져오기
        List<Article> articleList = articleRepository.findAll();
        // 1개 생성되었는 지 검증
        assertThat(articleList.size()).isEqualTo(1);
        // 처음(1개밖에 없지만) article 의 title 과 content 가 위에서 설정한 값과 일치하는 지 검증
        assertThat(articleList.get(0).getTitle()).isEqualTo(title);
        assertThat(articleList.get(0).getContent()).isEqualTo(content);
    }

    // GET Mapping(전체 SELECT) TEST
    @DisplayName("findAllArticles: GET MAPPING(전체 SELECT)")
    @Test
    public void findAllArticles() throws Exception {
        // given(테스트 실행 준비)
        // 위와는 다르게 Builder 방식으로 생성
        final String url = "/api/articles";
        final String title = "testTitle";
        final String content = "testContent";
        articleRepository.save(Article.builder().title(title).content(content).build());

        // when(테스트 실행)
        // perform() 메서드로 요청 전송(ResultActions 객체 반환)
        final ResultActions resultActions = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // then(테스트 결과 검증)
        // 음답코드 및 title 과 content 값 검증
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    // GET Mapping(SELECT) TEST
    @DisplayName("findArticle: GET Mapping(SELECT)")
    @Test
    public void findArticle() throws Exception {
        // given(테스트 실행 준비)
        // url 에 URI 변수 {id} 사용
        final String url = "/api/articles/{id}";
        final String title = "testTitle";
        final String content = "testContent";
        Article savedArticle = articleRepository.save(Article.builder().title(title).content(content).build());

        // when(테스트 실행)
        // perform() 메서드로 요청 전송. savedArticle 의 Id 값을 URI 변수로 넘겨줌.
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()).accept(MediaType.APPLICATION_JSON));

        // then(테스트 결과 검증)
        // 음답코드 및 title 과 content 값 검증
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }

    // Delete Mapping(DELETE) TEST
    @DisplayName("deleteArticle: Delete Mapping(DELETE)")
    @Test
    public void deleteArticle() throws Exception {
        // given(테스트 실행 준비)
        // url 에 URI 변수 {id} 사용
        final String url = "/api/articles/{id}";
        final String title = "testTitle";
        final String content = "testContent";
        Article savedArticle = articleRepository.save(Article.builder().title(title).content(content).build());

        // when(테스트 실행)
        // perform() 메서드로 요청 전송. savedArticle 의 Id 값을 URI 변수로 넘겨줌.
        ResultActions resultActions = mockMvc.perform(delete(url, savedArticle.getId()));

        // then(테스트 결과 검증)
        // 모든 article 목록을 받아온 후에 assertThat 을 이용하여 size 가 0임을 확인
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList.size()).isEqualTo(0);
    }

    // PUT Mapping(UPDATE) TEST
    @DisplayName("updateArticle: PUT Mapping(UPDATE)")
    @Test
    public void updateArticle() throws Exception {
        // given(테스트 실행 준비)
        // url 에 URI 변수 {id} 사용
        final String url = "/api/articles/{id}";
        final String title = "testTitle";
        final String content = "testContent";
        Article savedArticle = articleRepository.save(Article.builder().title(title).content(content).build());

        // 수정된 title, content 설정
        final String newTitle = "newTitle";
        final String newContent = "newContent";

        // Update 를 위한 dto 설정
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest(newTitle, newContent);

        // when(테스트 실행)
        // perform() 메서드로 요청 전송. savedArticle 의 Id 값을 URI 변수로 넘겨줌.
        // CREATE 와 마찬가지로 직렬화 수행
        ResultActions resultActions = mockMvc.perform(put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(updateArticleRequest)));

        // then(테스트 결과 검증)
        // 응답 코드 확인
        resultActions.andExpect(status().isOk());
        
        // given 에서 설정한 id 값으로 article 객체 담기
        Article article = articleRepository.findById(savedArticle.getId()).get();
        
        // 담아온 Article 객체의 값이 수정된 title, content 설정 변수와 같은 지 확인
        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }
}