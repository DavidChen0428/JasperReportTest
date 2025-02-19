package com.project.david.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.david.dao.MemberRepository;
import com.project.david.model.Gender;
import com.project.david.model.Member;
import com.project.david.service.MemberService;

// 方法不可能沒有動作 -> 異常處理
// 參數有規則 -> 邏輯處理
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberRepository memberRepository;

	@Override
	public void addMember(Member member) {
		memberRepository.save(member);
	}

	@Override
	public List<Member> selectMemberAll() {
		return memberRepository.findAll();
	}

	@Override
	public Member selectMemberById(int id) {
		return memberRepository.findById(id).get(0);
	}

	@Override
	public List<Member> selectMemberByAddress(String address) {
		return memberRepository.findByAddress(address);
	}

	@Override
	public List<Member> selectMemberByGender(String genderstr) {
		return memberRepository.findByGender(Gender.valueOf(genderstr));
	}

	@Override
	public Member loginMember(String username, String password) {
		Member member = memberRepository.findByUsername(username).get(0);
		if (member.getPassword().equals(password)) {
			return member;
		} else {
			// 登入失敗
			return null;
		}
	}

	@Override
	public boolean checkUsernameBeenUsed(String username) {
		boolean isUsernameBeenUsed = false;
		if (memberRepository.findByUsername(username).size() != 0) {
			isUsernameBeenUsed = true;
		} else {
			isUsernameBeenUsed = false;
		}
		return isUsernameBeenUsed;
	}

	@Override
	public void updateMember(Member member) {
		memberRepository.save(member);
	}

	@Override
	public void deleteMemberById(int id) {
		memberRepository.deleteById(id);
	}

}
