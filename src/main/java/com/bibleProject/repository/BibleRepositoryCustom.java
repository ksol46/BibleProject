package com.bibleProject.repository;

import org.springframework.data.domain.Page;

import com.bibleProject.dto.BibleSearchDto;
import com.bibleProject.entity.Bible;

public interface BibleRepositoryCustom {

	//성경데이터를 가져오지 않을까!
	Page<Bible> getBiblePage (BibleSearchDto bibleSearchDto);
}
