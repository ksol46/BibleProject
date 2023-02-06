package com.bibleProject.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bibleProject.dto.MemberFormDto;
import com.bibleProject.entity.Member;
import com.bibleProject.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	//회원가입 화면
	@GetMapping(value = "/sign")
	public String memberSign(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberSign";
	}
	
	//회원가입 버튼 클릭시 실행되는 메소드
	@PostMapping(value = "/sign")
	public String memberSign(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "member/memberSign";
		}
		
		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberSign";
		}
		
		return "redirect:/";
	}

	@GetMapping(value = "/login")
	public String loginMember() {
		return "member/memberLogin";
	}
	
	
	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "member/memberLogin";
	}
	
}
