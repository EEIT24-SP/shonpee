
package com.shonpee.shonpee;

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

import aj.org.objectweb.asm.Type;

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
				if (memberBean.getUserAccount().equals(session.getAttribute("UserName"))) {
					model.addAttribute("acc", memberBean);
					session.setAttribute("accid", memberBean.getMemberId());
				}
			}
			return "account/profile";
		}
	}

	@PostMapping(value = ("/main/acc"))
	public String test2(@ModelAttribute MemberBean MB, HttpSession session, Model model) {
		List<MemberBean> list = MR.findAll();
//		System.out.println(MR.findAll());
		for (MemberBean memberBean : list) {
			if (memberBean.getUserAccount().equals(session.getAttribute("UserName"))) {
				MB.setMemberId(memberBean.getMemberId());
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
			if (memberBean.getUserAccount().equals(Name)) {
				List<ProductBean> list1 = PR.findAll();
				for (ProductBean productBean : list1) {
					if (productBean.getMemberBean().getUserAccount().equals(Name)) {
						model.addAttribute("item", productBean);
						// 預設z
						System.out.println("product = "+productBean.getProductid());
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
		System.out.println("list======"+list2);
		for (MemberBean memberBean : list) {
			// 會員底下搜尋 如過購物車數量為0,則執行productBean新增置購物車
			for (CartBean cartBean : list2) {   //購買重複商品
				// 如果有商品,則判別商品ID是否重複,重複則自行遞增
				if (cartBean.getProductBean().getProductid().equals(Id)) {
					System.out.println("iiidddd"+cartBean.getMemberId());
					cartBean.setTotalPrice(Integer.toString(
							Integer.parseInt(cartBean.getTotalPrice()) / Integer.parseInt(cartBean.getQuantity())));
					cartBean.setQuantity(
							Integer.toString(PB.getProductStock() + Integer.parseInt(cartBean.getQuantity())));
					cartBean.setTotalPrice(Integer.toString(
							Integer.parseInt(cartBean.getTotalPrice()) * Integer.parseInt(cartBean.getQuantity())));
					CR.save(cartBean);
					System.out.println("tooooooooooooop = "+CR.findAll());
					return "redirect:/main-page/item";
				}
			}
			for (ProductBean productBean : list1) {  //購買新的˙商品
				if (productBean.getProductid().equals(Id)
						&& productBean.getMemberBean().getUserAccount().equals(Name)) {
//					 System.out.println(productBean.getMemberBean().getUser_Account().equals("bee567"));
//					System.out.println("productBean = "+productBean);
					CB.setQuantity(Integer.toString(PB.getProductStock()));
					CB.setTotalPrice(Integer.toString(PB.getProductStock() * productBean.getProductPrice()));
					CB.setProductBean(productBean);
					CB.setMemberId(productBean.getMemberBean().getUserAccount());
					CR.save(CB);
					System.out.println("bbbotommmm"+CR);
				}
			}
		}
		return "redirect:/main-page/item";
	}

	@RequestMapping("/main-page/cart")
	public String cart(HttpSession session, Model model, CartBean CB,String checkout) {
		if(checkout!=null) {
			System.out.println(checkout);
//			System.out.println(session);
//			System.out.println(CB);
			CartBean result = null;
			if(CB!=null&&CB.getCartId()!=null) {
				System.out.println("進來了1");
//				CB.setQuantity(Integer.toString(PB.getProduct_Stock()));
//				CB.setTotal_Price(Integer.toString(PB.getProduct_Stock() * productBean.getProduct_Price()));
//				CB.setProductBean(productBean);
//				CB.setMember_Id(productBean.getMemberBean().getUser_Account());
//				CR.save(CB);
			}
//			CR.save(CB);
			List<CartBean> list = CR.findAll();
			session.setAttribute("checkoutCB", list);
			return "redirect:/checkout";
		}else {
			List<CartBean> list = CR.findAll();
			
			System.out.println("ccccccrrrrrr = "+list);
			System.out.println("ccccccrrrrrr = "+session);
//			System.out.println(checkout);
//			for(CartBean cartBean:list) {
//				System.out.println("我是CaBean"+ cartBean.getProductBean().getProduct_Stock());
//			}
			model.addAttribute("cartitem", list);
			return "cart";
		}
		
	}

//	@PostMapping(value = ("/main-page/cart"))
//	public String cartview(CartBean CB, HttpSession session, Model model, String checkout, String delete, String CKBX) {
//		System.out.println("frecex"+delete);
//		System.out.println(CKBX);
//		System.out.println(checkout);
//
//		//取的複數ID
//		String[] split = CKBX.split(",", -1);
//		for (int i = 0; i < split.length; i++) {
//			System.out.println("數量 = "+split[i]);
//		}
//
//		System.out.println("我是CB" + CB.getMember_Id());
//		System.out.println("我是CBstock" + CB.getProductBean().getProduct_Stock());
//		System.out.println("我是QY" + CB.getQuantity());
//		System.out.println("我是CBstock" + Integer.toString(CB.getProductBean().getProduct_Price()));
//		System.out.println("我是CBstock" + CB.getProductBean().getProduct_Photo());
//		System.out.println("我是CBstock" + CB.getTotal_Price());
//		System.out.println("我是ID" + CB.getCart_Id());
//		// DELETE方法
//		if (delete != null && checkout == null) {
//			List<CartBean> list = CR.findAll();
//			for (CartBean cartBean : list) {
//				if (cartBean.getCart_Id().equals(Integer.parseInt(delete))) {
//					System.out.println("成功刪除CB=" + cartBean.getCart_Id() + "DELETE=" + delete);
//					CR.deleteById(Integer.parseInt(delete));
//					return "redirect:/main-page/cart";
//				}
//				if (cartBean.getCart_Id().equals(97)) {
//					System.out.println("我是這裡的" + cartBean.getCart_Id());
//					return "redirect:/main-page/cart";
//				}
//			}
//
//		} else {
//			return "redirect:/main-page/cart";
//
//		}
//
//		return "redirect:/main-page/cart";
//	}
//	
//	@RequestMapping("/main-page/cart")
//	public String tocheck(CartBean cart,HttpSession session, Model model) {
//		System.out.println("cart:"+cart);
////		System.out.println();
////		System.out.println();
//		return "/checkout";
//	}
	

	
}
