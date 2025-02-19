package com.project.david.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.david.model.Member;
import com.project.david.service.impl.MemberServiceImpl;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class RegisterLoginController {
	@Autowired
	MemberServiceImpl memberServiceImpl;
	
	@PostMapping("/login")
	public String loginMember(@RequestParam String username,@RequestParam String password,HttpSession session) {
		Member member=memberServiceImpl.loginMember(username, password);
		if(member!=null) {
			session.setAttribute("M", member);
			return "Member login success";
		}else {
			return "Member login fails, please try again.";
		}
	}
	
	@PostMapping("/register")
	public String registerMember(@RequestBody Member member) {
		if(!memberServiceImpl.checkUsernameBeenUsed(member.getUsername())) {
			memberServiceImpl.addMember(member);
			return "Member register successful.";
		}else {
			return "Member register failure : Username repeat.";
		}
		
	}
}
