package com.bibleProject.dto;


import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
public class PostWriteDto {

	private Long id; // 게시글 식별 코드
	
	private String book;
	private int chapter;
	private int verse;
	private String contents;
	
	@NotNull(message = "구약 신약 구분은 필수입니다.")
	private String category;

	@NotNull (message = "글제목은 필수 입력값입니다.")
	private String title;
	
	@NotNull (message = "글 내용 입력은  필수입니다..")
	private String detail; // 게시글 내용

	private String img_name; // 이미지 이름

	private String img_url; // 이미지 링크
}
