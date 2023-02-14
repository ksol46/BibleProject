package com.bibleProject.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.bibleProject.dto.BibleDto;
import com.bibleProject.dto.PostDto;
import com.bibleProject.dto.PostFormDto;
import com.bibleProject.entity.Bible;
import com.bibleProject.entity.Member;
import com.bibleProject.entity.Post;
import com.bibleProject.repository.BibleRepository;
import com.bibleProject.repository.MemberRepository;
import com.bibleProject.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BiblePostService {

	private final MemberRepository memberRepository;
	private final BibleRepository bibleRepository;
	private final PostRepository postRepository;

	// 말씀 검색
	@Transactional(readOnly = true)
	public List<BibleDto> findingBible(String searchQuery) {

		// 파싱하기
		boolean shortcontents = searchQuery.matches("^([ㄱ-ㅎ|가-힣]){1} \\d{1,3}[:]\\d{1,3}$");
		boolean longcontents = searchQuery.matches("^([ㄱ-ㅎ|가-힣]){1} \\d{1,3}[:]\\d{1,3}[-]\\d{1,3}$");

		List<Bible> bibleList = new ArrayList<>();

		if (shortcontents == true) {
			String first = searchQuery.replaceFirst(" ", "/");
			String[] second = first.split("/");
			String book = second[0];
			String fifth = second[1];
			String[] sixth = fifth.split(":");
			Integer chapter = Integer.parseInt(sixth[0]);
			Integer verse = Integer.parseInt(sixth[1]);

			bibleList = bibleRepository.findByBookAndChapterAndVerse(book, chapter, verse);

		} else if (longcontents == true) { // 시/139:10-20
			String first = searchQuery.replaceFirst(" ", "/");
			String[] second = first.split("/");
			String book = second[0];
			String fifth = second[1];
			String[] sixth = fifth.split(":");
			Integer chapter = Integer.parseInt(sixth[0]);
			String seventh = sixth[1];
			String[] eight = seventh.split("-");
			Integer verse1 = Integer.parseInt(eight[0]);
			Integer verse2 = Integer.parseInt(eight[1]);

			bibleList = bibleRepository.findByBookAndChapterAndVerseBetween(book, chapter, verse1, verse2);
			
		} else {
			
		}

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
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Member member = memberRepository.findByEmail(email);
		post.setMember(member);
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
		Post post = postRepository.findById(post_id).orElseThrow(EntityNotFoundException::new);

		PostFormDto postFormDto = PostFormDto.of(post);

		return postFormDto;

	}

	// 게시글 수정
	public Long updatePostFormDto(PostFormDto postFormDto) {
		System.out.println(postFormDto.getId());
		Post post = postRepository.findById(postFormDto.getId()).orElseThrow(EntityNotFoundException::new);

		post.updatePost(postFormDto);

		return post.getId();
	}

	// 로그인한 사용자와 게시글을 등록한 사용자와 같은지 검사
	@Transactional(readOnly = true)
	public boolean validatePost(Long post_id, String email) {
		Member curMember = memberRepository.findByEmail(email);
		Post post = postRepository.findById(post_id).orElseThrow(EntityNotFoundException::new);
		Member savedMember = post.getMember();

		if (!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())) {
			return false;
		}
		return true;
	}

	// 주문 삭제
	public void deletePost(Long post_id) {
		Post post = postRepository.findById(post_id).orElseThrow(EntityNotFoundException::new);

		postRepository.delete(post);
	}

}