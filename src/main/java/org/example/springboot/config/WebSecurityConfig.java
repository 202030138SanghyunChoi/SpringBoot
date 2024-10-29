package org.example.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

// Spring 설정 Annotation
@Configuration
// 시큐리티 설정 Annotation
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    // 보안 설정을 커스터마이즈할 수 있는 인터페이스
    public WebSecurityCustomizer webSecurityCustomizer() {
        // ignoring()은 인증을 안하겠다(시큐리티 적용 X). h2 랑 정적파일들
        return (web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"), new AntPathRequestMatcher("/static/**/**")));
    }

    @Bean
    // 보안 필터
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 악의적인 요청을 막는 Security 를 끔.(수업동안 내가 사용해야됨)
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((request) -> {
            // 로그인 하는 페이지 관련은 인증 안함(회원가입 포함)
            // requestMatchers(antMatcher("/login"))로 특정 URL 과 매치되는 URL find
            // permitAll() 이 모든 권한 부여. 인증 요청 X
            request.requestMatchers(antMatcher("/login")).permitAll();
            request.requestMatchers(antMatcher("/signup")).permitAll();
            request.requestMatchers(antMatcher("/user")).permitAll();

            // 앞서 허용한 인증들 이외 설정하지 않은 모든 경로(anyRequest())에 인증 필요 설정
            request.anyRequest().authenticated();
        });

        // 로그인 설정(로그인 시 동작)
        http.formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/articles", true).permitAll());

        // 로그아웃 설정(로그아웃 시 동작)
        // 로그아웃 시 로그인 페이지로 이동하고 세션 정보 삭제
        http.logout(logout -> logout.logoutSuccessUrl("/login").invalidateHttpSession(true));

        // HttpSecurity 객체를 build() 하여 SecurityFilterChain 객체로 보안 설정 활성화
        return http.build();
    }

    // 비밀번호 인코더(암호화)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}