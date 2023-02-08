package com.bibleProject.dto;

import lombok.*;

@Getter
@Setter
public class BibleDto {
	
	private Long id; //성경 식별 코드
	
	private String category; // 구, 신약 구분
	
	private String book; // 성경 책 이름
	
	private int chapter; // 성경 장 수
	
	private int verse; // 성경 절 수
	
	private String contents; // 성경 구절
	
}
