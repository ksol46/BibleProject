package com.bibleProject.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="bible")
@Getter
@Setter
@ToString
public class Bible {
	@Id
	@Column(name="bible_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //성경식별자
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
	private String category; //구약 신약 분류
	
	private String book; // 성경 책 이름
	
	private int chapter; // 성경 장 수
	
	private int verse; // 성경 절 수
	
	private String contents; // 성경 구절
	
	
	
}
