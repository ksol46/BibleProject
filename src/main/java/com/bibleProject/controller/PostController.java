package com.bibleProject.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bibleProject.dto.PostWriteDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/post")
@Controller
@RequiredArgsConstructor
public class PostController {
	
	//묵상하기 (글쓰기) 페이지 보여줌
	@GetMapping(value="/write")
	public String PostWrite(Model model) {
		model.addAttribute("postwrite", new PostWriteDto());
		return "post/write";
	}
	
	//묵상하기 글쓰기
	@PostMapping(value="/write")
	public String PostInsert(@Valid PostWriteDto postWriteDto, BindingResult bindingResult,
			Model model, @RequestParam("itemImgFile") MultipartFile itemImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "post/write";
		}
		
		return "redirect:/";
	}

	
	//묵상리스트 페이지 보여줌
	@GetMapping(value="/list")
	public String postList(Model model) {
		model.addAttribute("post",new PostWriteDto());
		return "post/list";
	}
}
