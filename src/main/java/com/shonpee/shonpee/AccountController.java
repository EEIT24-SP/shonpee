package com.shonpee.shonpee;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

	// 個人資料頁
	@RequestMapping(value = ("/main-page/acc"))
	public String profile1(HttpSession session, Model model, MemberBean MB) {
		String UserName = String.valueOf(session.getAttribute("UserName"));
        if (UserName.isEmpty() || UserName ==null||UserName == "null") {
			return "redirect:/login-page";
		} else {
			List<MemberBean> list = MR.findAll();
			for (MemberBean memberBean : list) {
				if (memberBean.getUserAccount().equals(session.getAttribute("UserName"))) {
					model.addAttribute("acc", memberBean);
					session.setAttribute("accphoto", memberBean.getMemberPhoto());
					session.setAttribute("accid", memberBean.getMemberId());
				}
			}
			return "account/profile";
		}
	}


	@PostMapping(value = ("/main-page/acc"))
	public String test2(@ModelAttribute MemberBean MB, HttpSession session, Model model,
			@RequestParam(value = "file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String filepath = uploadpath + "/" + fileName;
		//檔案不是空才寫入
		if (!fileName.isEmpty()) {
			try {
				file.transferTo(new File(filepath));
				List<MemberBean> list = MR.findAll();
				for (MemberBean memberBean : list) {
					if (memberBean.getUserAccount().equals(session.getAttribute("UserName"))) {
						MB.setMemberId(memberBean.getMemberId());
						MB.setPassword(memberBean.getPassword());
						MB.setMemberPhoto("/pic/upload/" + fileName);
						MR.save(MB);
					}
				}
				return "redirect:/main-page/acc";
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//如果照片空值,寫入自有照片路徑
		List<MemberBean> list = MR.findAll();
		for (MemberBean memberBean : list) {
			if (memberBean.getUserAccount().equals(session.getAttribute("UserName"))) {
				MB.setMemberId(memberBean.getMemberId());
				MB.setPassword(memberBean.getPassword());
				MB.setMemberPhoto(memberBean.getMemberPhoto());
				MR.save(MB);
			}
		}
		return "redirect:/main-page/acc";
	}

	// 購物車頁
	@RequestMapping("/main-page/cart")
	public String cart(HttpSession session, Model model, CartBean CB) {
		Object Name = session.getAttribute("UserName");
		List<CartBean> list = CR.findAll();
		ArrayList<CartBean> arrL = new ArrayList<CartBean>();
		List<MemberBean> list2 = MR.findAll();
		String UserName = String.valueOf(session.getAttribute("UserName"));
        if (UserName.isEmpty() || UserName ==null||UserName == "null") {
			return "redirect:/login-page";
		}
		for (MemberBean memberBean : list2) {
			if (memberBean.getUserAccount().equals(session.getAttribute("UserName"))) {
				model.addAttribute("acc", memberBean);
				session.setAttribute("accphoto", memberBean.getMemberPhoto());
				session.setAttribute("accid", memberBean.getMemberId());
			}
		}

		// 搜尋會員,顯示符合當前帳號的購物車資料
		for (CartBean cartBean : list) {
			if (cartBean.getMemberId().equals(Name) && cartBean.getProductBean().getProductStatus() == null) {
				arrL.add(cartBean);
			}else if(cartBean.getProductBean().getProductStatus()!=null) {	
				CR.delete(cartBean);
			}
			Collections.reverse(arrL);
			model.addAttribute("cartitem", arrL);
		}
		return "cart";
	}


	@PostMapping(value = ("/main-page/cart"))
	public String cartview(CartBean CB, HttpSession session, Model model, String checkout, String delete, String CKBX) {
		//取的複數ID,再依照符合ID加入結帳選單
		// DELETE方法
		if (delete != null && checkout == null) { 
			List<CartBean> list = CR.findAll();
			for (CartBean cartBean : list) {
				if (cartBean.getCartId().equals(Integer.parseInt(delete))) {
					CR.deleteById(Integer.parseInt(delete));
					int a = (Integer) session.getAttribute("cartsize");
					int cartsize = a - 1;
					session.setAttribute("cartsize", cartsize);
					return "redirect:/main-page/cart";
				}
			}
		} else if(checkout == null) {
			return "redirect:/main-page/cart";			
		} else if (checkout != null) {
//			String[] split = CKBX.split(",", -1);
			String[] splitTotalPrice = CB.getTotalPrice().split(",", -1);
			String[] splitQuantity = CB.getQuantity().split(",", -1);
			String[] splittypeValue1 = CB.getTypeValue1().split(",",-1);
			String[] splittypeValue2 = CB.getTypeValue2().split(",",-1);
			String Name = session.getAttribute("UserName").toString();
			List<CartBean> list = CR.findAll();
			ArrayList<CartBean> CBlist = new ArrayList<CartBean>();
			//session要清空
			session.setAttribute("checkoutCB","");
			String[] splitid = CKBX.split(",", -1);
			for (int i = 0; i < splitid.length; i++) {
				for (CartBean cartBean : list) {
					if (cartBean.getCartId().equals(Integer.parseInt(splitid[i]))) {
						cartBean.setQuantity(splitQuantity[i]);
						cartBean.setTotalPrice(splitTotalPrice[i]);
						cartBean.setTypeValue1(splittypeValue1[i]);
						cartBean.setTypeValue2(splittypeValue2[i]);
						CR.save(cartBean);
						
						CBlist.add(cartBean);
					}
				}
			}	
			// 搜尋會員,顯示符合當前帳號的購物車資料
			session.setAttribute("checkoutCB", CBlist);
			return "redirect:/checkout";
		}
		return "redirect:/main-page/cart";
	}

}

