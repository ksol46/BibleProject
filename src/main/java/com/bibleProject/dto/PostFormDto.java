package com.bibleProject.dto;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.bibleProject.entity.Member;
import com.bibleProject.entity.Post;

import lombok.*;

@Getter
@Setter
public class PostFormDto { // create 모달 폼

	private Long id; // 게시글 식별 코드
	
	private Member member;

	@NotNull(message = "글제목은 필수 입력값입니다.")
	private String title;

	@NotNull(message = "글 내용 입력은  필수입니다.")
	private String detail; // 게시글 내용
	
	private Long post_img_id; //게시글 이미지 식별자
	
	private String bible_contents;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Post createPost() {
		return modelMapper.map(this, Post.class);
	}
	
	public static PostFormDto of (Post post) {
		return modelMapper.map(post, PostFormDto.class);
	}
	

}
