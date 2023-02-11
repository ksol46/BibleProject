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
	private Long id; //성경식별자
	
	@Column(name="book")
	private String book; // 성경 책 이름
	
	@Column(name="chapter")
	private int chapter; // 성경 장 수
	
	@Column(name="verse")
	private int verse; // 성경 절 수
	
	@Column(name="content")
	private String content; // 성경 구절
	
	
	
}
