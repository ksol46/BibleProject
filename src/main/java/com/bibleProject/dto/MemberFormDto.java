package com.bibleProject.dto;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;

import lombok.*;

@Getter
@Setter
public class MemberFormDto { //회원가입 폼
	
	@NotBlank(message = "닉네임은 필수 입력 값입니다.")
	private String nickname;
	
	@NotEmpty(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String email;
	
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
	@Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
	private String password;
}
