package com.bibleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bibleProject.dto.BibleDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/bible")
@Controller
@RequiredArgsConstructor
public class BibleController {
	
	//성경 읽기 화면을 보여줌.
	@GetMapping(value = "/read")
	public String readBible(Model model) {
		model.addAttribute("bibleDto", new BibleDto());
		
		return "bible/bibleView";
	}
	
	
}
