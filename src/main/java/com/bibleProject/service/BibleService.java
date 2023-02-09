package com.bibleProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibleProject.dto.BibleDto;
import com.bibleProject.entity.Bible;
import com.bibleProject.repository.BibleRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BibleService {

	private final BibleRepository bibleRepository;
	
	@Transactional(readOnly = true)
	public List<BibleDto> findingBible(String book, Integer chapter, Integer verse, String contents) {
		List<Bible> bibleList = bibleRepository.findByBookAndChapterAndVerse(book, chapter, verse, contents);
		List<BibleDto> bibleDtoList = new ArrayList<>();
		
		for (Bible bible : bibleList) {
			BibleDto bibleDto = BibleDto.of(bible);
			bibleDtoList.add(bibleDto);
		}
		
		return bibleDtoList;	
		
	}
	
}

