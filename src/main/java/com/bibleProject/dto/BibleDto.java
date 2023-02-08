package com.bibleProject.dto;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.modelmapper.ModelMapper;

import com.bibleProject.entity.Bible;

import lombok.*;

@Getter
@Setter
public class BibleDto {
	
	private Long id; //성경 구절 코드
	
	private String book; // 성경 책 이름
	
	private int chapter; // 성경 장 수
	
	private int verse; // 성경 절 수
	
	private String contents; // 성경 구절
	
	//private static ModelMapper modelMapper = new ModelMapper();
	
	/*
	public Bible insertBible() {
		return modelMapper.map(this,Bible.class);
	}
	*/
}
