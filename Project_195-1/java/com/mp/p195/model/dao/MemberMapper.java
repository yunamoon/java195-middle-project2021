package com.mp.p195.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mp.p195.model.dto.Admin;
import com.mp.p195.model.dto.Member;

@Mapper
public interface MemberMapper {
	List<Member> listMember();
	Member idChk(@Param("id") String id);
	Member memberInfo(@Param("Id") String Id, @Param("Password") String Password);
	void memberCreate(Member member);
	void memberUpdate(Member member);
	void  memberDelete(@Param("id") String id, @Param("password") String password);
	Member pwChk(@Param("password") String password);
	Admin adminInfo(@Param("Id") String Id, @Param("Password") String Password);
}
