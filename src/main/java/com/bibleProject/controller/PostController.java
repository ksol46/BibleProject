package com.bibleProject.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bibleProject.dto.BibleDto;
import com.bibleProject.dto.PostDto;
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
	public String bibleSearch(@RequestParam(value = "searchQuery", defaultValue = "") String searchQuery, Model model) {

		model.addAttribute("postwrite", new PostFormDto());

		List<BibleDto> bibleDtoList = biblePostService.findingBible(searchQuery);
		model.addAttribute("bibleDtoList", bibleDtoList);
		System.out.println("bibleDtoList: "+bibleDtoList);
		return "post/write";
	}

	// 묵상하기 글쓰기
	@PostMapping(value = "/write")
	public @ResponseBody ResponseEntity PostInsert(@RequestBody @Valid PostFormDto postFormDto,
			BindingResult bindingResult, Model model) {

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
		List<PostDto> postList = biblePostService.getPostDto();
		model.addAttribute("postList", postList);

		return "post/board";
	}

	// 게시글 보기
	@GetMapping(value = "/view/{post_id}")
	public String PostView(Model model, @PathVariable("post_id") Long post_id) {
		PostFormDto postFormDto = biblePostService.getPostFormDto(post_id);
		model.addAttribute("postFormDto", postFormDto);
		return "post/view";
	}

	// 게시글 수정
	@PostMapping(value = "/view/{post_id}")
	public String postUpdate(@Valid PostFormDto postFormDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "post/view";
		}
		
		try {
			biblePostService.updatePostFormDto(postFormDto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "게시글수정 중 에러가 발생하였습니다.");
			return "post/view";
		}
		
		return "redirect:/post/board";
	}
	
	//게시글 삭제
	@DeleteMapping("/view/{post_id}/delete")
	public @ResponseBody ResponseEntity deletePost(@PathVariable("post_id") Long post_id, Principal principal) {
		if(!biblePostService.validatePost(post_id, principal.getName())) {
			return new ResponseEntity<String>("주문 삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
		}
		biblePostService.deletePost(post_id);
		return new ResponseEntity<Long>(post_id, HttpStatus.OK);
	}
}
