package com.mp.p195.model.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.mp.p195.model.dto.Item;
import com.mp.p195.model.dto.OrderItem;
import com.mp.p195.model.dto.ViewCart;

@Mapper
public interface OrderItemMapper {

	public List<ViewCart> viewCartMemNum2(int memNum);
	public void delete(String memNum);
	public void update(Long orderNum, int orderCount);
	public void purchase(int memNum);
	public List<ViewCart> myPurchaseList(int memNum);
	public void save(String orderNum, int memNum);
}