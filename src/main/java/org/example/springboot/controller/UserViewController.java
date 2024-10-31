package org.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    // RestController 가 아니므로 http 상태 코드 리턴이 아닌 template 파일 이름 리턴
    // get 매핑
    @GetMapping("/login")
    public String login() {
        // html 파일 이름 리턴
        return "login";
    }

    // get 매핑
    @GetMapping("signup")
    public String signup() {
        // html 파일 이름 리턴
        return "signup";
    }
}
