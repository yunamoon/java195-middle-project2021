package com.mp.p195.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mp.p195.model.dao.ItemMapper;
import com.mp.p195.model.dto.Item;
import com.mp.p195.model.dto.Member;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	ItemMapper itemMapper;

	@RequestMapping("listItem")
	// 상품 목록 사이트
	public String listAll(Model model) {
		// 상품 목록
		model.addAttribute("Item", itemMapper.listAll());
		// 코딩 시 상품 상세정보 보는 용도 (지워도 정상 작동)
		model.addAttribute("ItemAll", itemMapper.listAllInfo());
		return "/item/search";
	}

	@RequestMapping("/search")
	public String searchItem(Model model,
			@RequestParam(defaultValue = "NULL", value = "itemColor") String itemColor,
			@RequestParam(defaultValue = "NULL", value = "itemSize") String itemSize,
			@RequestParam(defaultValue = "NULL", value = "itemCategory") String itemCategory,
			@RequestParam(defaultValue = "0", value = "itemPrice1") String itemPrice1,
			@RequestParam(defaultValue = "0", value = "itemPrice2") String itemPrice2
			) {
		// 상품 검색 메소드
		if(itemCategory.equals("NULL")) {
			if (itemSize.equals("NULL")) {
				if (itemColor.equals("NULL")) {
					// category X, color X, size X 
					if(itemPrice1.equals("0")) {
						if(itemPrice2.equals("0")) {
							// price X
							model.addAttribute("Item", itemMapper.listAll());
						}else {
							// ~price2
							model.addAttribute("Item", itemMapper.listAll2(itemPrice2));
						}
					}else {
						if(itemPrice2.equals("0")) {
							// price1~
							model.addAttribute("Item", itemMapper.listAll1(itemPrice1));
						}else {
							// price1~price2
							model.addAttribute("Item", itemMapper.listAll3(itemPrice1, itemPrice2));
						}
					}
					
				} else {
					// category X, color O, size X		
					if(itemPrice1.equals("0")) {
						if(itemPrice2.equals("0")) {
							// price X
							model.addAttribute("Item", itemMapper.itemSearchColor(itemColor));
						}else {
							// ~price2
							model.addAttribute("Item", itemMapper.itemSearchColor2(itemColor, itemPrice2));
						}
					}else {
						if(itemPrice2.equals("0")) {
							// price1~
							model.addAttribute("Item", itemMapper.itemSearchColor1(itemColor, itemPrice1));
						}else {
							// price1~price2
							model.addAttribute("Item", itemMapper.itemSearchColor3(itemColor, itemPrice1, itemPrice2));
						}
					}
					
				}
			} else {
				if (itemColor.equals("NULL")) {
					// category X, color X, size O
					if(itemPrice1.equals("0")) {
						if(itemPrice2.equals("0")) {
							// price X
							model.addAttribute("Item", itemMapper.itemSearchSize(itemSize));
						}else {
							// ~price2
							model.addAttribute("Item", itemMapper.itemSearchSize2(itemSize, itemPrice2));
						}
					}else {
						if(itemPrice2.equals("0")) {
							// price1~
							model.addAttribute("Item", itemMapper.itemSearchSize1(itemSize, itemPrice1));
						}else {
							// price1~price2
							model.addAttribute("Item", itemMapper.itemSearchSize3(itemSize, itemPrice1, itemPrice2));
						}
					}
					
				} else {
					// category X, color O, size O
					if(itemPrice1.equals("0")) {
						if(itemPrice2.equals("0")) {
							// price X
							model.addAttribute("Item", itemMapper.itemSearchColorAndSize(itemColor, itemSize));
						}else {
							// ~price2
							model.addAttribute("Item", itemMapper.itemSearchColorAndSize2(itemColor, itemSize, itemPrice2));
						}
					}else {
						if(itemPrice2.equals("0")) {
							// price1~
							model.addAttribute("Item", itemMapper.itemSearchColorAndSize1(itemColor, itemSize, itemPrice1));
						}else {
							// price1~price2
							model.addAttribute("Item", itemMapper.itemSearchColorAndSize3(itemColor, itemSize, itemPrice1, itemPrice2));
						}
					}
					
				}
			}
		}else {
			if (itemSize.equals("NULL")) {
				if (itemColor.equals("NULL")) {
					// category O, color X, size X 
					if(itemPrice1.equals("0")) {
						if(itemPrice2.equals("0")) {
							// price X
							model.addAttribute("Item", itemMapper.itemSearchCategory(itemCategory));
						}else {
							// ~price2
							model.addAttribute("Item", itemMapper.itemSearchCategory2(itemCategory, itemPrice2));
						}
					}else {
						if(itemPrice2.equals("0")) {
							// price1~
							model.addAttribute("Item", itemMapper.itemSearchCategory1(itemCategory, itemPrice1));
						}else {
							// price1~price2
							model.addAttribute("Item", itemMapper.itemSearchCategory3(itemCategory, itemPrice1, itemPrice2));
						}
					}
					
				} else {
					// category O, size X, color O		
					if(itemPrice1.equals("0")) {
						if(itemPrice2.equals("0")) {
							// price X
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndColor(itemColor, itemCategory));
						}else {
							// ~price2
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndColor2(itemColor, itemCategory, itemPrice2));
						}
					}else {
						if(itemPrice2.equals("0")) {
							// price1~
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndColor1(itemColor, itemCategory, itemPrice1));
						}else {
							// price1~price2
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndColor3(itemColor, itemCategory, itemPrice1, itemPrice2));
						}
					}
					
				}
			} else {
				if (itemColor.equals("NULL")) {
					// category O, color X, size O
					if(itemPrice1.equals("0")) {
						if(itemPrice2.equals("0")) {
							// price X
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndSize(itemCategory, itemSize));
						}else {
							// ~price2
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndSize2(itemCategory, itemSize, itemPrice2));
						}
					}else {
						if(itemPrice2.equals("0")) {
							// price1~
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndSize1(itemCategory, itemSize, itemPrice1));
						}else {
							// price1~price2
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndSize3(itemCategory, itemSize, itemPrice1, itemPrice2));
						}
					}
					
				} else {
					// category O, color O, size O
					if(itemPrice1.equals("0")) {
						if(itemPrice2.equals("0")) {
							// price X
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndColorAndSize(itemCategory, itemColor, itemSize));
						}else {
							// ~price2
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndColorAndSize2(itemCategory, itemColor, itemSize, itemPrice2));
						}
					}else {
						if(itemPrice2.equals("0")) {
							// price1~
							model.addAttribute("Item", itemMapper.itemSearchCategoryAndColorAndSize1(itemCategory, itemColor, itemSize, itemPrice1));
							}else {
							// price1~price2
								model.addAttribute("Item", itemMapper.itemSearchCategoryAndColorAndSize3(itemCategory, itemColor, itemSize, itemPrice1, itemPrice2));
							}
					}
					
				}
			}
		}
		
		return "/item/search";
	}
	
	@RequestMapping("/searchItem")
	public String  searchItem(@RequestParam("categorySearchBar")String itemCategory, 
			@RequestParam(defaultValue = "NULL", value="keyword") String keyword, Model model ) {
		List<Item> searchItem;
		if (keyword.equals("NULL")) {
			searchItem = itemMapper.itemSearchCategory(itemCategory);
		}else {
			searchItem = itemMapper.searchItemNameAndCategory(keyword, itemCategory);
		}
		
		model.addAttribute("Item", searchItem);
		
		return "/item/search";
	}
	

	// 상품 상세보기 contentView?상품번호
	@RequestMapping("/contentView")
	public String contentView(Model model, int itemNum) {
		model.addAttribute("contentView", itemMapper.contentView(itemNum));
		return "/item/contentView";
	}
	
	
	// orderTable DB에 추가하기
	String result;
	boolean is = false;
	DecimalFormat frm = new DecimalFormat("000");
	DecimalFormat memFrm = new DecimalFormat("00000");
	ArrayList<String> orderNum;
	int listInt = 1;
	@RequestMapping("/addCart")
	public String contentView(Model model, HttpSession session, int itemNum, int orderCount) {
		Member user = (Member)session.getAttribute("user"); 
		System.out.println(user);
		int memNum = user.getMemNum();
		//int memNum = 100;
		String memNumS = memFrm.format(memNum);
		Date nowDate = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(nowDate);
		
		String orderInt = frm.format(listInt);
		
		String result = (date+""+memNumS+""+orderInt);
		
		while(true) {
			if(itemMapper.orderNumSelect(result, memNum)==0) {
				itemMapper.addCart(result, memNum, itemNum, orderCount);
				break;
			}
			result = sequenceJAVA(date, memNumS);
			System.out.println(result);
		}

		return "redirect:/item/contentView?itemNum="+itemNum;
	}
	
	// DB 추가 중복확인(시퀀스) 무한반복 메소드
	public String sequenceJAVA(String date, String memNum) {
		is = false;
		 
		if(orderNum==null) {
			orderNum = new ArrayList<String>();
		}
		
		String orderInt = frm.format(listInt);

		if(orderNum.contains(orderInt)) {
			listInt ++;
		
		}else {
			orderNum.add(orderInt);
			is = true;
		}
		
		return date+""+memNum+""+orderInt;
	}
	
	// 상품 등록 페이지
	@RequestMapping("/itemAddForm")
	public void itemAddForm() {
	}
	
	// 실제 등록 기능
	@RequestMapping("/upload")
	public String upload(Model model, Item item) {
		System.out.println(item);
		itemMapper.upload(item);
		return "redirect:search";
	}
	
	// 상품 수정 페이지
	@RequestMapping("/updateForm")
	public void updateForm(Model model, int itemNum) {
		model.addAttribute("contentView", itemMapper.contentView(itemNum));
	}
	// 실제 수정 기능
	@RequestMapping("/update")
	public String update(Model model, Item item) {
		itemMapper.update(item);
		int itemNum = item.getItemNum();
		return "redirect:contentView?itemNum="+itemNum;
	}
	
	// 삭제 기능 delete?상품번호
	@RequestMapping("/delete")
	public String delete(int itemNum) {
		itemMapper.delete(itemNum);
		return "redirect:search";
	}

}
