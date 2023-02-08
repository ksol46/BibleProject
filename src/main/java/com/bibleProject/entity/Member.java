package com.bibleProject.entity;

import javax.persistence.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.bibleProject.dto.MemberFormDto;

import lombok.*;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member{
	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //회원식별자
	
	private String nickname; //회원닉네임
	
	@Column(unique = true)
	private String email; //이메일 아이디
	
	private String password; // 비밀번호

	public static Member createMember (MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setNickname(memberFormDto.getNickname());
		member.setEmail(memberFormDto.getEmail());
		
		String password = passwordEncoder.encode(memberFormDto.getPassword());
		member.setPassword(password);
		
		return member;
	}
	
	
}
