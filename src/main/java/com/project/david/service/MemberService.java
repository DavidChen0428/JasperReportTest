package com.project.david.service;

import java.util.List;

import com.project.david.model.Member;

public interface MemberService {
	// create
	void addMember(Member member);
	
	// read
	List<Member> selectMemberAll();
	Member selectMemberById(int id);
	List<Member> selectMemberByAddress(String address);
	List<Member> selectMemberByGender(String genderstr);
	
	Member loginMember(String username,String password);
	boolean checkUsernameBeenUsed(String username);
	
	// update
	void updateMember(Member member);
	
	// delete
	void deleteMemberById(int id);
	
}
