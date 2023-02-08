package com.bibleProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bibleProject.entity.Bible;

public interface BibleRepository extends JpaRepository <Bible, Long> {

	
	
}
