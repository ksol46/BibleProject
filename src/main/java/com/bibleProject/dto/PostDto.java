package com.bibleProject.dto;


import java.time.format.DateTimeFormatter;

import com.bibleProject.entity.Post;

import lombok.*;

@Getter
@Setter
public class PostDto {
	
	public PostDto (Post post) {
		this.post_id = post.getId();
		this.bible_contents = post.getBible_contents();
		this.title = post.getTitle();
		this.regDate = post.getReg_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	private Long post_id; // 게시글 아이디

	private String title; // 게시글 제목

	private String regDate; // 게시글 작성 날짜
	
	private String contents_title;
	
	private String bible_contents;
	

	}

