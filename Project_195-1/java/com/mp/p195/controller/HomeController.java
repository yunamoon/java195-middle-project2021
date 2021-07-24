package com.mp.p195.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mp.p195.model.dao.ItemMapper;
import com.mp.p195.model.dto.Item;

@Controller

public class HomeController {
	@Autowired
	ItemMapper itemMapper;
	
	@RequestMapping("/")
	public String main(){
		return "redirect:index";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		List<Item> item = itemMapper.listMain(); //10
		int a=0; 
		if(item.size()>8) {					//
			a = item.size()-8; //a=2
			for(int i=0; i<a; i++) { //두번 반복
				item.remove(item.size()-1);
			}
		}	
		System.out.println(item);
		model.addAttribute("Item", item);
		
		return "index";
	}
	

	@RequestMapping("/aboutus")
	public String aboutUs() {
		return "/aboutUs";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("/faqs")
	public String faqs() {
		return "faqs";
	}
	
}