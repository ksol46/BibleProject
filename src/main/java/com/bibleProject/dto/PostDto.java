package com.bibleProject.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bibleProject.entity.Post;

import lombok.*;

@Getter
@Setter
public class PostDto {

	public PostDto(Post post) {
		this.post_id = post.getId();
		this.title = post.getTitle();
		this.regDate = post.getReg_time();
	}

	private Long post_id; // 게시글 아이디

	private String title; // 게시글 제목

	private LocalDateTime regDate; // 게시글 작성 날짜
	
	private List<PostFormDto> postFormDtoList = new ArrayList<>();

	}

