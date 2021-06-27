package com.shonpee.shonpee;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shonpee.shonpee.repository.CartRepository;
import com.shonpee.shonpee.repository.MemberRepository;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.domain.MemberBean;
import com.shonpee.shonpee.domain.ProductBean;
@Controller
public class AccountController {

	@Value("${upload-path}")
	private String uploadpath;
	@Autowired
	MemberRepository MR;
	@Autowired
	CartRepository CR;
	@Autowired
	ProductRepository PR;

	@RequestMapping(value = ("/main-page/acc"))
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

	@PostMapping(value = ("/main-page/acc"))
	public String test2(@ModelAttribute MemberBean MB, HttpSession session, Model model,@RequestParam(value = "file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String filepath =uploadpath+"/"+fileName;
		try {
			file.transferTo(new File(filepath));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<MemberBean> list = MR.findAll();
		for (MemberBean memberBean : list) {
			if (memberBean.getUserAccount().equals(session.getAttribute("UserName"))) {
				MB.setMemberId(memberBean.getMemberId());
				MB.setPassword(memberBean.getPassword());
				MB.setMemberPhoto("/pic/upload/"+fileName);
				MR.save(MB); 
			}
		}
		return "redirect:/main-page/acc";
	}


	@RequestMapping("/main-page/cart")
	public String cart(HttpSession session, Model model, CartBean CB) {
		Object Name = session.getAttribute("UserName");
		List<CartBean> list = CR.findAll();
        ArrayList<CartBean> arrL = new ArrayList<CartBean>();
		//搜尋會員,顯示符合當前帳號的購物車資料
		for (CartBean cartBean : list) {
			if (cartBean.getMemberId().equals(Name)) {
		        arrL.add(cartBean);
				model.addAttribute("cartitem", arrL); 
			}	
		}
		return "cart";  
	}

	@PostMapping(value = ("/main-page/cart"))
	public String cartview(CartBean CB, HttpSession session, Model model, String checkout, String delete, String CKBX) {
//		System.out.println(delete);
		System.out.println(CKBX);
		System.out.println(CB.getTypeValue1());
//		System.out.println(checkout);
//		//取的複數ID,再依照符合ID加入結帳選單
//		String[] split = CKBX.split(",", -1);
//		for (int i = 0; i < split.length; i++) {
//			System.out.println("我是SP"+split[i]);
//		}  
		// DELETE方法
		if (delete != null && checkout == null) {
			List<CartBean> list = CR.findAll();
			for (CartBean cartBean : list) {
				if (cartBean.getCartId().equals(Integer.parseInt(delete))) {
					System.out.println("成功刪除CB=" + cartBean.getCartId() + "DELETE=" + delete);
					CR.deleteById(Integer.parseInt(delete));
					int a = (Integer)session.getAttribute("cartsize");
					int cartsize =a-1;
					session.setAttribute("cartsize",cartsize);
					return "redirect:/main-page/cart";
				}
			}

		} else if(checkout!=null) {
			System.out.println("送出表單");
			return "redirect:/main-page/cart";

		}

		return "redirect:/main-page/cart";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//程式範本暫存區	
//進入商品面	
//	@RequestMapping(value = ("/main-page/item"))
//	public String itemview(HttpSession session, Model model, MemberBean MB) {
//		Object Name = session.getAttribute("UserName");
//		List<MemberBean> list = MR.findAll();
//		for (MemberBean memberBean : list) {
//			if (memberBean.getUserAccount().equals(Name)) {
//				List<ProductBean> list1 = PR.findAll();
//				for (ProductBean productBean : list1) {
//					if (productBean.getMemberBean().getUserAccount().equals(Name)
//							&& productBean.getProductid().equals(10)) {
//						model.addAttribute("item", productBean);
//						String[] split = productBean.getProductPhoto().split(",");
//						model.addAttribute("photolist", split);
//						for (int i = 0; i < split.length; i++) {
//							System.out.println("我是SP" + split.length);
//						}
//
//						// 預設
//						System.out.println(productBean.getProductid());
//						session.setAttribute("itemid", productBean.getProductid());
//					}
//				}
//			}
//
//		}
//
//		return "Bear-item";
//	}

//送出商品參數	
//	@PostMapping(value = ("/main-page/item"))
//	public String item(HttpSession session, Model model, MemberBean MB, CartBean CB, ProductBean PB) {
//		Object Name = session.getAttribute("UserName");
//		Object Id = session.getAttribute("itemid");
//		List<MemberBean> list = MR.findAll();
//		List<ProductBean> list1 = PR.findAll();
//		List<CartBean> list2 = CR.findAll();
//		for (MemberBean memberBean : list) {
//			// 會員底下搜尋 如過購物車數量為0,則執行productBean新增置購物車
//			for (CartBean cartBean : list2) {
//				// 如果有商品,則判別商品ID是否重複,重複則自行遞增
//				if (cartBean.getProductBean().getProductid().equals(Id)) {
//					System.out.println(cartBean.getMemberId());
//					cartBean.setTotalPrice(Integer.toString(
//							Integer.parseInt(cartBean.getTotalPrice()) / Integer.parseInt(cartBean.getQuantity())));
//					cartBean.setQuantity(
//							Integer.toString(PB.getProductStock() + Integer.parseInt(cartBean.getQuantity())));
//					cartBean.setTotalPrice(Integer.toString(
//							Integer.parseInt(cartBean.getTotalPrice()) * Integer.parseInt(cartBean.getQuantity())));
//					CR.save(cartBean);
//					return "redirect:/main-page/item";
//				}
//			}
//			for (ProductBean productBean : list1) {
//				if (productBean.getProductid().equals(Id)) {
////					 System.out.println(productBean.getMemberBean().getUser_Account().equals("bee567"));
//					CB.setQuantity(Integer.toString(PB.getProductStock()));
//					CB.setTotalPrice(Integer.toString(PB.getProductStock() * productBean.getProductPrice()));
//					CB.setProductBean(productBean);
//					CB.setMemberId((String) Name);
//					System.out.println("我是OBJ" + (String) Name);
//					CR.save(CB);
//				}
//			}
//		}
//		return "redirect:/main-page/item";
//	}
	
	
	
	
	
	
	

}
