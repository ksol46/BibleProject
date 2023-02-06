package com.bibleProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bibleProject.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findByEmail(String email);
}
