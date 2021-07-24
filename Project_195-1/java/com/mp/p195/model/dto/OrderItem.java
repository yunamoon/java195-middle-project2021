package com.mp.p195.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class OrderItem {
	
	private long orderNum;
	private int memNum;
	private int itemNum;
	private Date orderDate;
	private int orderCount;
	private String cartStatus;
	
}
