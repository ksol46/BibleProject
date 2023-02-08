package com.bibleProject.entity;

import javax.persistence.*;
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
	
	private Long post_img_id; //게시글 이미지 식별자
	
	private String title; // 게시글 제목
	
	private String detail; //게시글 내용
	
	private String img_name; //이미지 이름
	
	private String img_url; //이미지 링크
	
}
