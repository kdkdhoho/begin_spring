package com.example.begin_spring.controller;

import com.example.begin_spring.domain.Member;
import com.example.begin_spring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createForm";
    }

    /**
     * /members/new에서 post 방식으로 값이 들어오면 실행.
     * createForm.html에서 값을 받게 됨. <input name="name">에 의해 MemberForm.name에 값이 저장.
     * <input name="sex"> 이면 MemberForm.sex에 값이 저장.
     * DTO 역할인 MemberForm으로 원하는 데이터를 이용.
     */
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setSex(form.getSex());
        memberService.join(member);

        System.out.println("member.getName() = " + member.getName());
        System.out.println("member.getSex() = " + member.getSex());

        return "redirect:/"; // 홈화면으로 redirect
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
