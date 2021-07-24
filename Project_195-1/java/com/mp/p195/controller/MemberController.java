package com.mp.p195.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mp.p195.model.dao.MemberMapper;
import com.mp.p195.model.dto.Admin;
import com.mp.p195.model.dto.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberMapper memberMapper;

	// member list
	@RequestMapping("/listMember")
	public String memberListAll(Model model) {
		model.addAttribute("members", memberMapper.listMember());
		return "/member/listMember";
	}
	
	// 마이 페이지
	@RequestMapping("/mainMember")
	public String main() {
		return "/member/mainMember";
	}
	
	// 회원가입
	@RequestMapping("/joinMember")
	public String join() {
		return "/member/joinMember";
	}

	@RequestMapping("/createMember")
	public String create(Member member) {
		System.out.println(member);
		memberMapper.memberCreate(member);
		return "/member/loginMember";
	}

	//동일 id 체크 메소드
	@RequestMapping(value = "/membertest", method = RequestMethod.POST)
	public @ResponseBody Member idOverlap(@RequestParam("id") String id) {
		return memberMapper.idChk(id);
	}
	
	// 로그인
	@RequestMapping("/loginMember")
	public String login() {
		return "/member/loginMember";
	}


	@PostMapping("/login")
	public String login(String Id, String Password, Model model, HttpSession session) {
		Member member = memberMapper.memberInfo(Id, Password);
		System.out.println(member);
		Admin admin = memberMapper.adminInfo(Id, Password);
		System.out.println("1");
		System.out.println(admin);
		
		if (member != null) {
			System.out.println(member);
			session.setAttribute("user", member);
			return "redirect:/index";
		} else {
			if(admin !=null) {
				System.out.println("2");
				session.setAttribute("admin", admin);
				System.out.println(admin);				
				return "redirect:/index";
			}
			System.out.println("3");				
			System.out.println(admin);				
			model.addAttribute("failMsg", "다시 입력해 주십시오");
			return "/member/loginMember";
		}		
	}

	// 로그아웃
	@RequestMapping("/logoutMember")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("admin");
		return "redirect:/index";
	}

	// member 정보
	@RequestMapping("/infoMember")
	public String infoView(HttpSession session) {
		Member temp = (Member) session.getAttribute("user");
		temp = memberMapper.memberInfo(temp.getId(), temp.getPassword());
		session.setAttribute("user", temp);
		return "/member/infoMember";
	}
	
	// member 수정
	@RequestMapping("/updateMember")
	public String update(HttpSession session, Member member) {
		System.out.println(member);
		memberMapper.memberUpdate(member);
		session.setAttribute("user", member);
		return "/index";
	}
	
	// member 삭제
	@RequestMapping("/deleteMember")
	public String delete() {
		return "/member/deleteMember";
	}

	@RequestMapping("/delete")
	public String delete22(HttpSession session, String id, String password) {
		session.removeAttribute("user");
		session.invalidate();
		memberMapper.memberDelete(id, password);
		return "/index";
	}
	
	//password 일치 확인 메소드
	@RequestMapping(value = "/memberpassword", method = RequestMethod.POST)
	public @ResponseBody Member pwOverlap(@RequestParam("password") String password) {
		return memberMapper.pwChk(password);
	}
	
}