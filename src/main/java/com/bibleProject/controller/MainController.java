package com.bibleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.*;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class MainController {

	@GetMapping(value="/")
	public String main() {
		
		return "main";
	}
	
	
}
