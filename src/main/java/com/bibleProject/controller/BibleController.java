package com.bibleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bibleProject.dto.BibleDto;
import com.bibleProject.entity.Bible2;
import com.bibleProject.service.BibleService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/bible")
@Controller
@RequiredArgsConstructor
public class BibleController {
	//private final BibleService bibleService;
	
	//성경 읽기 화면을 보여줌.
	@GetMapping(value = "/read")
	public String readBible(Model model) {
		model.addAttribute("bibleDto", new BibleDto());
		
		Bible2.insertBible();
		
		return "bible/bibleView";
	}
	
	
}
