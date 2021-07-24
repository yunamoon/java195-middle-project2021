package com.mp.p195.model.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mp.p195.model.dto.Item;

@Mapper
public  interface ItemMapper {
	// 상품 목록
	List<Item> listAll();
	List<Item> listAll1(String itemPrice1);
	List<Item> listAll2(String itemPrice2);
	List<Item> listAll3(String itemPrice1, String itemPrice2);
	
	List<Item> listAllInfo();
	
	// 상세 정보
	List<Item> contentView(int itemName);
	
	// 상품 목록-검색
	List<Item> itemSearchCategoryAndColorAndSize(String itemCategory, String itemColor, String itemSize);
	List<Item> itemSearchCategoryAndColorAndSize1(String itemCategory, String itemColor, String itemSize, String itemPrice1);
	List<Item> itemSearchCategoryAndColorAndSize2(String itemCategory, String itemColor, String itemSize, String itemPrice2);
	List<Item> itemSearchCategoryAndColorAndSize3(String itemCategory, String itemColor, String itemSize, String itemPrice1, String itemPrice2);
	
	List<Item> itemSearchColorAndSize(String itemColor, String itemSize);
	List<Item> itemSearchColorAndSize1(String itemColor, String itemSize, String itemPrice1);
	List<Item> itemSearchColorAndSize2(String itemColor, String itemSize, String itemPrice2);
	List<Item> itemSearchColorAndSize3(String itemColor, String itemSize, String itemPrice1, String itemPrice2);
	
	List<Item> itemSearchCategoryAndColor(String itemColor, String itemCategory);
	List<Item> itemSearchCategoryAndColor1(String itemColor, String itemCategory, String itemPrice1);
	List<Item> itemSearchCategoryAndColor2(String itemColor, String itemCategory, String itemPrice2);
	List<Item> itemSearchCategoryAndColor3(String itemColor, String itemCategory, String itemPrice1, String itemPrice2);
	
	List<Item> itemSearchCategoryAndSize(String itemCategory, String itemSize);
	List<Item> itemSearchCategoryAndSize1(String itemCategory, String itemSize, String itemPrice1);
	List<Item> itemSearchCategoryAndSize2(String itemCategory, String itemSize, String itemPrice2);
	List<Item> itemSearchCategoryAndSize3(String itemCategory, String itemSize, String itemPrice1, String itemPrice2);
	
	List<Item> itemSearchSize(String itemSize);
	List<Item> itemSearchSize1(String itemSize, String itemPrice1);
	List<Item> itemSearchSize2(String itemSize, String itemPrice2);
	List<Item> itemSearchSize3(String itemSize, String itemPrice1, String itemPrice2);
	
	List<Item> itemSearchColor(String itemColor);
	List<Item> itemSearchColor1(String itemColor, String itemPrice1);
	List<Item> itemSearchColor2(String itemColor, String itemPrice2);
	List<Item> itemSearchColor3(String itemColor, String itemPrice1, String itemPrice2);
	
	List<Item> itemSearchCategory(String itemCategory);
	List<Item> itemSearchCategory1(String itemCategory, String itemPrice1);
	List<Item> itemSearchCategory2(String itemCategory, String itemPrice2);
	List<Item> itemSearchCategory3(String itemCategory, String itemPrice1, String itemPrice2);
	
	//상품명 검색
	List<Item> searchItemNameAndCategory(String keyword, String itemCategory);
	
	// 상품관리
	void upload(Item item);
	void delete(int itemNum);
	void update(Item item);
	
	// 시퀀스
	Integer orderNumSelect(String orderNum, int memNum);
	void addCart(@Param("result") String result, @Param("memNum")int memNum,
			@Param("itemNum")int itemNum, @Param("orderCount")int orderCount);
	
	List<Item>listMain();
}
