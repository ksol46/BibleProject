package com.bibleProject.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bibleProject.entity.Post;

public interface PostRepository extends JpaRepository <Post, Long> {
	
}
