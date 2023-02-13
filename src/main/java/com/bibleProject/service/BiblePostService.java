package com.bibleProject.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibleProject.dto.BibleDto;
import com.bibleProject.dto.PostDto;
import com.bibleProject.dto.PostFormDto;
import com.bibleProject.entity.Bible;
import com.bibleProject.entity.Post;
import com.bibleProject.repository.BibleRepository;
import com.bibleProject.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BiblePostService {

	private final BibleRepository bibleRepository;
	private final PostRepository postRepository;

	// 검색
	@Transactional(readOnly = true)
	public List<BibleDto> findingBible(String searchQuery) {

		// 파싱하기
		String first = searchQuery.replaceFirst(" ", "/");
		String[] second = first.split("/");
		String book = second[0];
		String fifth = second[1];
		String[] sixth = fifth.split(":");
		Integer chapter = Integer.parseInt(sixth[0]);
		Integer verse = Integer.parseInt(sixth[1]);

		List<Bible> bibleList = bibleRepository.findByBookAndChapterAndVerse(book, chapter, verse);
		List<BibleDto> bibleDtoList = new ArrayList<>();

		// 엔티티 객체를 dto객체로 변환
		for (Bible bible : bibleList) {
			BibleDto bibleDto = BibleDto.of(bible);
			bibleDtoList.add(bibleDto);
		}

		return bibleDtoList;

	}

	// 게시글 등록
	public Long savePost(PostFormDto postFormDto) throws Exception {
		Post post = postFormDto.createPost();
		postRepository.save(post);
		return post.getId();
	}

	// 게시글 라스트
	@Transactional(readOnly = true)
	public List<PostDto> getPostDto() {
		List<Post> postList = postRepository.findAll();
		List<PostDto> postDtoList = new ArrayList<>();

		for (Post post : postList) {
			PostDto postDto = new PostDto(post);
			postDtoList.add(postDto);
		}

		return postDtoList;
	}

	// 게시글 상세보기
	public PostFormDto getPostFormDto(Long post_id) {
		Post post = postRepository.findById(post_id)
				.orElseThrow(EntityNotFoundException::new);

		PostFormDto postFormDto = PostFormDto.of(post);

		return postFormDto;

	}

	// 게시글 수정
	public Long updatePostFormDto(PostFormDto postFormDto) {
		System.out.println(postFormDto.getId());
		Post post = postRepository.findById(postFormDto.getId())
				.orElseThrow(EntityNotFoundException::new);
		
		post.updatePost(postFormDto);
		
		return post.getId();
	}

}
