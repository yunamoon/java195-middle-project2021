package com.mp.p195.model.dto;

import lombok.Data;

@Data
public class ViewCart {

	private long orderNum;
	private int memNum;
	private int itemNum;
	private String itemImage;
	private String itemName;
	private String itemColor;
	private String itemSize;
	private int itemPrice;
	private int orderCount;
	private String orderDate;
}
