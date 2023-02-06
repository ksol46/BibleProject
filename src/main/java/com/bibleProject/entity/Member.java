package com.bibleProject.entity;

import javax.persistence.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.bibleProject.dto.MemberFormDto;

import lombok.*;

@Entity
@Table(name="biblemember")
@Getter
@Setter
@ToString
public class Member{
	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nickname;
	
	@Column(unique = true)
	private String email;
	
	private String password;

	public static Member createMember (MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setNickname(memberFormDto.getNickname());
		member.setEmail(memberFormDto.getEmail());
		
		String password = passwordEncoder.encode(memberFormDto.getPassword());
		member.setPassword(password);
		
		return member;
	}
	
	
}
