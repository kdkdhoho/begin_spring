package com.example.begin_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 1) MVC. 매개변수 없음.
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "World!");
        return "hello";
    }

    // 2) 템플릿 엔진(thymeleaf 사용). URI로 매개변수 입력 받음
    @GetMapping("firstTemplates")
    public String firstTemplates(Model model, @RequestParam(name="name")String name) {
        model.addAttribute("name", name);
        return "firstTemplates";
    }

    // 3) API 방식
    // `@ResponseBody` 를 붙여주어야 HTTP body부에 그대로 return
    // 그냥 바로 String 값을 return 할 수도 있음. 하지만 보통 사용하는 것은 객체를 전달
    @GetMapping("hello-Member")
    @ResponseBody
    public Member helloMember(@RequestParam("id") Long id,
                              @RequestParam("name") String name,
                              @RequestParam("sex") String sex) {
        Member member = new Member(id, name, sex);
        return member;
    }

    // API 방식에서 객체를 전송 시, JSON 방식으로 전송되는 것을 확인하기 위한 static class
    static class Member {
        private Long id;
        private String name;
        private String sex;

        public Member(Long id, String name, String sex) {
            this.id = id;
            this.name = name;
            this.sex = sex;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
