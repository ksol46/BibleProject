package com.bibleProject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bibleProject.dto.BibleDto;
import com.bibleProject.dto.PostFormDto;
import com.bibleProject.service.BiblePostService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/post")
@Controller
@RequiredArgsConstructor
public class PostController {
	private final BiblePostService biblePostService;

	// 묵상하기 (글쓰기) 페이지 보여줌
	@GetMapping(value = "/write")
	public String PostWrite(Model model) {
		model.addAttribute("postwrite", new PostFormDto());
		return "post/write";
	}
	
	
	// 성경 구절 검색기능
	@GetMapping(value = "/search")
	public String bibleSearch(@RequestParam(value = "searchQuery",defaultValue = "") String searchQuery,Model model) {
		
		model.addAttribute("postwrite", new PostFormDto());
		
		List<BibleDto> bibleDtoList = biblePostService.findingBible(searchQuery);
		model.addAttribute("bibleDtoList", bibleDtoList);

		return "post/write";
	}
	

	// 묵상하기 글쓰기
	@PostMapping(value = "/write")
	public @ResponseBody ResponseEntity PostInsert(@RequestBody @Valid PostFormDto postFormDto, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("에러입니다.", HttpStatus.OK);		
		}
			
			Long postId;
			
			try {
				postId = biblePostService.savePost(postFormDto);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Long>(postId, HttpStatus.OK);	
		}
	

	// 묵상리스트 게시판 보여줌
	@GetMapping(value = "/board")
	public String PostBoard(Model model) {
		model.addAttribute("board", new PostFormDto());
		return "post/board";
	}

}