package com.project.david.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.david.model.Gender;
import com.project.david.model.Member;

public interface MemberRepository extends JpaRepository<Member,Integer>{
	List<Member> findById(int id);
	List<Member> findByUsername(String username);
	List<Member> findByAddress(String address);
	List<Member> findByGender(Gender gender);
}
