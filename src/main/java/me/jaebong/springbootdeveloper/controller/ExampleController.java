package me.jaebong.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller//반환하는 값의 이름을 가진 뷰의 파일을 찾으라는 것!
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model){// 뷰로 데이터를 넘겨주는 모델 객체
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("이재봉");
        examplePerson.setAge(28);
        examplePerson.setHobbies(List.of("산책","게임"));

        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());

        return "example"; // example.html이라는 뷰 조회
    }

    @Setter
    @Getter
    class Person{
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
