package com.project.david.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.david.model.Member;
import com.project.david.service.impl.MemberServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberServiceImpl memberServiceImpl;
	
	@GetMapping("/allMember")
	public List<Member> allMember(){
		return memberServiceImpl.selectMemberAll();
	}
	
	@GetMapping("/selectMemberId/{id}")
	public Member selectMemberById(@PathVariable int id) {
		return memberServiceImpl.selectMemberById(id);
	}
	
	@GetMapping("/selectMemberAddress/{address}")
	public List<Member> selectMemberByAddress(@PathVariable String address){
		return memberServiceImpl.selectMemberByAddress(address);
	}
	
	@GetMapping("/selectMemberGender/{gender}")
	public List<Member> selectMemberByGender(@PathVariable String gender){
		return memberServiceImpl.selectMemberByGender(gender);
	}
	
	@PutMapping("/updateMember")
	public String updateMember(@RequestBody Member member) {
		memberServiceImpl.updateMember(member);
		return "Member update successfully.";
	}
	
	@DeleteMapping("/deleteMember/{id}")
	public String deleteMember(@PathVariable int id) {
		memberServiceImpl.deleteMemberById(id);
		return "Member delete successfully.";
	}
}
