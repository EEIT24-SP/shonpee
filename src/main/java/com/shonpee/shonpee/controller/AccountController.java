package com.shonpee.shonpee.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shonpee.shonpee.repository.CartRepository;
import com.shonpee.shonpee.repository.MemberRepository;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.domain.MemberBean;
import com.shonpee.shonpee.domain.ProductBean;

@Controller
public class AccountController {

	@Autowired
	MemberRepository MR;
	@Autowired
	CartRepository CR;
	@Autowired
	ProductRepository PR;

	@RequestMapping(value = ("/main/acc"))
	public String profile1(HttpSession session, Model model, MemberBean MB) {
		if (session.getAttribute("UserName") == null) {
			return "redirect:/login-page";
		} else {

			List<MemberBean> list = MR.findAll();
			for (MemberBean memberBean : list) {
				if (memberBean.getUser_Account().equals(session.getAttribute("UserName"))) {
					model.addAttribute("acc", memberBean);
					session.setAttribute("accid", memberBean.getMember_Id());
				}
			}
			return "account/profile";
		}
	}

	@PostMapping(value = ("/main/acc"))
	public String test2(@ModelAttribute MemberBean MB, HttpSession session, Model model) {
		List<MemberBean> list = MR.findAll();
		for (MemberBean memberBean : list) {
			if (memberBean.getUser_Account().equals(session.getAttribute("UserName"))) {
				MB.setMember_Id(memberBean.getMember_Id());
				MB.setPassword(memberBean.getPassword());
				MR.save(MB);
			}
		}
		return "redirect:/main/acc";
	}

	@RequestMapping(value = ("/main-page/item"))
	public String itemview(HttpSession session, Model model, MemberBean MB) {
		Object Name = session.getAttribute("UserName");
		List<MemberBean> list = MR.findAll();
		for (MemberBean memberBean : list) {
			if (memberBean.getUser_Account().equals(Name)) {
				List<ProductBean> list1 = PR.findAll();
				for (ProductBean productBean : list1) {
					if (productBean.getMemberBean().getUser_Account().equals(Name)) {
						model.addAttribute("item", productBean);
						// 預設z
						System.out.println(productBean.getProductid());
						session.setAttribute("itemid", productBean.getProductid());
					}
				}
			}

		}

		return "Bear-item";
	}

	@PostMapping(value = ("/main-page/item"))
	public String item(HttpSession session, Model model, MemberBean MB, CartBean CB, ProductBean PB) {
		Object Name = session.getAttribute("UserName");
		Object Id = session.getAttribute("itemid");
		List<MemberBean> list = MR.findAll();
		List<ProductBean> list1 = PR.findAll();
		List<CartBean> list2 = CR.findAll();
		for (MemberBean memberBean : list) {
			// 會員底下搜尋 如過購物車數量為0,則執行productBean新增置購物車
			for (CartBean cartBean : list2) {
				//如果有商品,則判別商品ID是否重複,重複則自行遞增
				if (cartBean.getProductBean().getProductid().equals(Id)) {
					System.out.println(cartBean.getMember_Id());
					cartBean.setTotal_Price(Integer.toString(Integer.parseInt(cartBean.getTotal_Price()) /Integer.parseInt( cartBean.getQuantity())));
					cartBean.setQuantity(Integer.toString(PB.getProduct_Stock()+Integer.parseInt(cartBean.getQuantity())));
					cartBean.setTotal_Price(Integer.toString(Integer.parseInt(cartBean.getTotal_Price()) *Integer.parseInt( cartBean.getQuantity())));
					CR.save(cartBean);
					return "redirect:/main-page/item";
				} 
			}
			for (ProductBean productBean : list1) {
				if (productBean.getProductid().equals(Id) && productBean.getMemberBean().getUser_Account().equals(Name)) {
//					 System.out.println(productBean.getMemberBean().getUser_Account().equals("bee567"));
					CB.setQuantity(Integer.toString(PB.getProduct_Stock()));
					CB.setTotal_Price(Integer.toString(PB.getProduct_Stock()*productBean.getProduct_Price()));
					CB.setProductBean(productBean);
					CB.setMember_Id(productBean.getMemberBean().getUser_Account());
					CR.save(CB);
				}
			}
		}
		return "redirect:/main-page/item";
	}
	
	
	@RequestMapping("/main-page/cart")
	public String cart(HttpSession session, Model model ,CartBean CB) {
		List<CartBean>list =CR.findAll();
//		for(CartBean cartBean:list) {
//			System.out.println("我是CaBean"+ cartBean.getProductBean().getProduct_Stock());
//		}
			model.addAttribute("cartitem", list);
		return "cart";
	}
	
	
	
	
	
	
	@PostMapping(value = ("/main-page/cart"))
	public String cartview(CartBean CB, HttpSession session, Model model) {
			System.out.println("我是CB"+ CB.getMember_Id());
			System.out.println("我是CBstock"+ CB.getProductBean().getProduct_Stock());
			System.out.println("我是QY"+ CB.getQuantity());
			System.out.println("我是CBstock"+ Integer.toString(CB.getProductBean().getProduct_Price()));
			System.out.println("我是CBstock"+ CB.getProductBean().getProduct_Photo());
			System.out.println("我是CBstock"+ CB.getTotal_Price());

		return "redirect:/main-page/cart";
	}
	
	
	

}