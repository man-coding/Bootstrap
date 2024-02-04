package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;

	@Test
	public void 회원등록() {

		Member member1 = Member.builder().id("chch2857").name("만코").password("1234").build();
		Member member2 = Member.builder().id("chch3088").name("만코").password("1234").build();

		repository.save(member1);
		repository.save(member2);
	}

	@Test
	public void 회원목록조회() {

		List<Member> list = repository.findAll();

		for (Member member : list) {
			System.out.println(member);
		}
	}
	
	@Test
	public void 회원단건조회() {
		
		Optional<Member> result = repository.findById("chch2857");
		
		Member member = result.get();
		System.out.println(member);
	}

	@Test
	public void 회원수정() {
		
		Optional<Member> result = repository.findById("chch3088");
		
		Member member = result.get();
		
		member.setPassword("2580");
		
		repository.save(member);
	}
	
	@Test
	public void 회원삭제() {
		
		repository.deleteById("chch3088");
	}
}
