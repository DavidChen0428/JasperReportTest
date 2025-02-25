package com.project.david.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.david.model.Member;
import com.project.david.service.impl.MemberServiceImpl;

@SpringBootTest
public class MemberServiceImplTest {
	@Autowired
	MemberServiceImpl memberServiceImpl;
	
	@Test
	public void test() {
		memberServiceImpl.addMember(new Member("小明王","MALE","小明","1234","4444","彰化"));
		System.out.println("add member success");
	}
}
