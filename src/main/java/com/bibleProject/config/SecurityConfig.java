package com.bibleProject.config;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bibleProject.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	MemberService memberService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//로그인에 대한 설정
		http.formLogin().loginPage("/members/login")
		.defaultSuccessUrl("/")
		.usernameParameter("email")
		.failureUrl("/members/login/error")
		.failureHandler(new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					org.springframework.security.core.AuthenticationException exception)
					throws IOException, ServletException {
				String errorMessage;
				 if (exception instanceof BadCredentialsException) {
			            errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
			        } else if (exception instanceof InternalAuthenticationServiceException) {
			            errorMessage = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
			        } else if (exception instanceof UsernameNotFoundException) {
			            errorMessage = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
			        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			            errorMessage = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
			        } else {
			            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다 관리자에게 문의하세요.";
			        }
							System.out.println(errorMessage);
				
			}
			
		})
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 url }
		.logoutSuccessUrl("/");
		
		//페이지 접근에 대한 설정
		http.authorizeHttpRequests()
		.mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
		.mvcMatchers("/", "/members/**", "/images/**").permitAll()
		.mvcMatchers("/post/**", "/bible/**").hasRole("member")
		.anyRequest().authenticated();
		
		//인증되지 않은 사용자가 리소스에 접근했을때 설정
		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
