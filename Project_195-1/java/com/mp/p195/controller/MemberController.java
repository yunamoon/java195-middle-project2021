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
	
	// 2021.04.09 _ 문유나 
	// 195 서비스 전체 회원 목록 호출
	@RequestMapping("/listMember")
	public String memberListAll(Model model) {
		model.addAttribute("members", memberMapper.listMember());
		return "/member/listMember";
	}
	
	// 2021.04.09 _ 문유나 
	// 상단의 mypage 아이콘 클릭 시, 회원 전용 메뉴 노출되는 페이지로 이동
	@RequestMapping("/mainMember")
	public String main() {
		return "/member/mainMember";
	}
	
	// 2021.04.09 _ 문유나 
	// 회원가입 html 호출
	@RequestMapping("/joinMember")
	public String join() {
		return "/member/joinMember";
	}
	
	// ㅊ
	// 회원 가입 정보 기입 후, submit 버튼 클릭시 해당 정보를 member DTO로 받아서 DB에 저장 
	// 회원 가입 완료 후, 로그인 페이지로 이동
	@RequestMapping("/createMember")
	public String create(Member member) {
		memberMapper.memberCreate(member);
		return "/member/loginMember";
	}
	
	// 2021.04.09 _ 문유나 
	// 회원 가입 시, ID 중복 여부 체크, ajax로 전달받은 id로 member db를 확인하고 일치하는 정보가 있을 경우
	// 해당 정보를 반환한다.
	@RequestMapping(value = "/membertest", method = RequestMethod.POST)
	public @ResponseBody Member idOverlap(@RequestParam("id") String id) {
		return memberMapper.idChk(id);
	}
	
	// 2021.04.09 _ 문유나 
	// 로그인 페이지로 이동
	@RequestMapping("/loginMember")
	public String login() {
		return "/member/loginMember";
	}

	// 2021.07.1 로그인 업데이트 _ 문유나
	// 사용자가 입력한 id와 password를 파라미터 값으로 받아서, db 내 일치 정보를 확인한다.
	// 먼저 member table을 확인하고 , 일하는 결과가 없을 시에 admin table을 확인한다.
	@PostMapping("/login")
	public String login(String Id, String Password, Model model, HttpSession session) {
		Member member = memberMapper.memberInfo(Id, Password);
		
		// member db 내 일치하는 결과가 있을 경우, 회원 로그인
		if (member != null) {
			System.out.println(member + "회원입니다.");
			session.setAttribute("user", member);
			return "redirect:/index";
		// member db내 일치하는 결과가 없을 경우, admin table 체크
		} else {
			Admin admin = memberMapper.adminInfo(Id, Password);
			
			// admin table 내 일치하는 정보가 존재 할 경우, admin login 
			if(admin !=null) {
				System.out.println(admin + "관리자입니다");
				session.setAttribute("admin", admin);			
				return "redirect:/index";
			// admin table에도 일치하는 정보가 없는 경우, 오류 메세지 반환
			} else { 
			model.addAttribute("failMsg", "아이디 혹은 비밀번호를 확인하여 주십시오.");
			return "/member/loginMember";
			}		
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