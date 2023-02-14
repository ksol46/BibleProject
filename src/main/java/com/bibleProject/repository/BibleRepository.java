package com.bibleProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bibleProject.entity.Bible;

public interface BibleRepository extends JpaRepository <Bible, Long> {
	List<Bible> findByBookAndChapterAndVerse(String book, Integer chapter, Integer verse);
	
	List<Bible> findByBookAndChapterAndVerseBetween(String book, Integer chapter, Integer start, Integer end);
}
