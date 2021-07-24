package com.mp.p195.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mp.p195.model.dao.OrderItemMapper;
import com.mp.p195.model.dto.Item;
import com.mp.p195.model.dto.Member;
import com.mp.p195.model.dto.OrderItem;
import com.mp.p195.model.dto.ViewCart;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController {

	@Autowired
	OrderItemMapper orderItemMapper;

	@RequestMapping("viewCartMemNum")
	public String viewCartMemNum(HttpSession session, Model model) {
		// 로그인x => nocart로 이동, 비회원일 때는 cookie를 통해 장바구니 기록? 추후에 논의하는 걸로^^ 일단은 이렇게 할게요
		int memNum = 0;

		// 로그인 상태이면 해당 멤버의 번호 가져오기
		if (session.getAttribute("user") != null) {
			Member mem = (Member) session.getAttribute("user");
			memNum = mem.getMemNum();
		}

		// 로그인x이면 nocart로, 로그인 상태이면 해당 멤버의 카트로 이동
		if (memNum == 0) {
			return "/orderItem/nocart";
		} else {
			List<ViewCart> viewCart = orderItemMapper.viewCartMemNum2(memNum);
			if(!viewCart.isEmpty()) {
				model.addAttribute("viewCart", viewCart);
			}
			return "/orderItem/viewCart";
		} 
	}

	@RequestMapping(value = "/delete")
	public String ajaxText(HttpServletRequest request) {

		String[] ajaxMsg = request.getParameterValues("valueArr");
		int size = ajaxMsg.length;
		for (int i = 0; i < size; i++) {
			orderItemMapper.delete(ajaxMsg[i]);
		}
		return "redirect:viewCartMemNum";

	}

	@RequestMapping(value = "/update")
	public String ajaxText2(HttpServletRequest request) { 

		String[] list = request.getParameterValues("list");
		String[] rowCheck = request.getParameterValues("rowCheck");
		for (int i = 0; i < list.length; i++) {
			System.out.println("checkbox =" + rowCheck[i] + " count =" + list[i]);
			orderItemMapper.update(Long.parseLong(rowCheck[i]), Integer.parseInt(list[i]));
		}
		return "redirect:viewCartMemNum";
	}

	@RequestMapping("purchase")
	public String purchase(HttpSession session, Model model) {// , HttpSession session
		// 로그인x => nocart로 이동, 비회원일 때는 cookie를 통해 장바구니 기록? 추후에 논의하는 걸로^^ 일단은 이렇게 할게요
		int memNum = 0;

		// 로그인 상태이면 해당 멤버의 번호 가져오기
		if (session.getAttribute("user") != null) {
			Member mem = (Member) session.getAttribute("user");
			memNum = mem.getMemNum();
		}

		if (memNum == 0) {
			return "/orderItem/nocart";
		} else {
			model.addAttribute("viewCart", orderItemMapper.viewCartMemNum2(memNum));
			return "/orderItem/purchase";
		}
	}
	@RequestMapping("save")
	public String save(HttpSession session, Model model, HttpServletRequest request) {// , HttpSession session
		int memNum = 0;
		
		if (session.getAttribute("user") != null) {
			Member mem = (Member) session.getAttribute("user");
			memNum = mem.getMemNum();
		}
		
		if (memNum == 0) {
			return "/orderItem/nocart";
		} else {
			String[] ajaxMsg = request.getParameterValues("valueArr");
			int size = ajaxMsg.length;
			for (int i = 0; i < size; i++) {
				orderItemMapper.save(ajaxMsg[i], memNum);
			}
			return "redirect:myPurchaseList";
		}
	}
	@RequestMapping("afterPurchase")
	public String afterPurchase(HttpSession session, Model model) {
		Member mem = (Member) session.getAttribute("user");
		model.addAttribute("viewCart", orderItemMapper.viewCartMemNum2(mem.getMemNum()));
//afterPurchase 하는 동안만 주석처리해두기
		Date nowDate = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(nowDate);		
		orderItemMapper.purchase(mem.getMemNum());
		return "/orderItem/afterPurchase";
	}
	
	@RequestMapping("myPurchaseList")
	public String myPurchaseList(HttpSession session, Model model) {
		Member mem = (Member) session.getAttribute("user");
		model.addAttribute("viewPurchase", orderItemMapper.myPurchaseList(mem.getMemNum()));
		return "/orderItem/myPurchaseList";
	}

}