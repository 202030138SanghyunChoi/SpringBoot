package org.example.springboot.controller;

import org.example.springboot.dto.AddUserRequest;
import org.example.springboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 컨트롤러기 때문에 template 파일 리턴 또는 redirect 로 리턴
@Controller
// 생성자 자동으로 의존성 주입
@RequiredArgsConstructor
public class UserAPIController {

    // 의존성 service 객체 생성
    private final UserService userService;

    // user PostMapping(회원 가입 매핑)
    @PostMapping("/user")
    public String signup(AddUserRequest addUserRequest) {
        // 서비스 save 메서드 사용(유저 추가 dto)
        userService.save(addUserRequest);

        // login(UserViewController 참고) API 로 이동
        return "redirect:/login";
    }

    @GetMapping("/loguot")
    // 로그 아웃 http request, response 필요
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 인증 정보 객체 선언
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // req 로 세션 삭제, res 로 redirect, 사용자 인증 정보 처리
        new SecurityContextLogoutHandler().logout(request, response, authentication);

        // login 으로 redirect
        return "redirect:/login";
    }
}