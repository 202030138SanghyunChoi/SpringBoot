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
        Person person = new Person();
        person.setId(1L);
        person.setName("이름");
        person.setAge(18);
        person.setHobbies(List.of("운동", "독서"));

        model.addAttribute("welcome", "HelloWorld");
        model.addAttribute("person", person);
        model.addAttribute("today", LocalDate.now());

        // resource/templates 폴더의 같은 이름의 html 파일 리턴
        return "test";
    }

    @Getter
    @Setter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
