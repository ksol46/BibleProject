package com.bibleProject.entity;

import javax.persistence.*;

import com.bibleProject.dto.PostFormDto;

import lombok.*;

@Entity
@Table(name="post")
@Getter
@Setter
@ToString
public class Post extends BaseEntity {
	
	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //게시글 식별자
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	private String title; // 게시글 제목
	
	@Lob
	private String detail; //게시글 내용
	
	@Lob
	private String bible_contents; //본문말씀

	public void updatePost(PostFormDto postFormDto) {
		this.bible_contents = postFormDto.getBible_contents();
		this.title = postFormDto.getTitle();
		this.detail = postFormDto.getDetail();
	}
	
	
}
