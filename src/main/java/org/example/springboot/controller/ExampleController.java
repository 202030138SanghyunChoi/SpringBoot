package org.example.springboot.controller;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// View 용 Controller 이기 때문에 Controller Annotation 추가
@Controller
public class ExampleController {

    @GetMapping("/test")
    public String test(Model model) {
        // 테스트용 사람 객체 생성 및 정보 설정
        Person person = new Person();
        person.setId(1L);
        person.setName("이름");
        person.setAge(18);
        person.setHobbies(List.of("운동", "독서"));

        // addAttribute 메서드를 통해 데이터를 view 로 전달
        model.addAttribute("welcome", "HelloWorld");
        model.addAttribute("person", person);
        model.addAttribute("today", LocalDate.now());

        // resource/templates 폴더의 같은 이름의 html 파일 이름 리턴
        return "test";
    }

    // 테스트용 사람 클래스 선언
    // Getter / Setter 메서드 자동 생성
    @Getter
    @Setter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
