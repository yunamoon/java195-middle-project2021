package com.mp.p195.model.dto;

import lombok.Data;

@Data
public class Member {
	
	private int memNum;
	private String id;
	private String password;
	private String name;
	private String email;
	private String birth;
	private String gender;
	private String address;
	private String phNum;
	private String card;
	
	public String getPassword() {
		return password;
	}
	public String getId() {
		return id;
	}
	public String getCard() {
		return card;
	}
	@Override
	public String toString() {
		return "Member [memNum=" + memNum + ", id=" + id + ", password=" + password + ", name=" + name + ", email="
				+ email + ", birth=" + birth + ", gender=" + gender + ", address=" + address + ", phNum=" + phNum
				+ ", card=" + card + "]";
	}
	
	
}