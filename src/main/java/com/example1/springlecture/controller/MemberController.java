package com.example1.springlecture.controller;

import com.example1.springlecture.domain.Member;
import com.example1.springlecture.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.util.List;

// @Controller가 붙은 객체는 스프링 실행 시 스프링컨테이너에 생성해 추가*관리한다.
// 이를 Spring Container에서 Spring Bean이 관리된다 라고한다.
@Controller
public class MemberController {

    private final MemberService memberService;

    // 생성자에 @Autowired가 붙으면 스프링이 '스프링 컨테이너에 있는' 객체를 연결해 준다.
    // 즉 MemberService 클래스는 @Service 가 붙어있어야 한다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "/members/createMemberForm.html";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());

        System.out.println("name = " + member.getName());
        memberService.join(member);

        return "/members/createdMember";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("members", memberList);

        return "members/memberList";
    }
}
