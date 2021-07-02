package com.shonpee.shonpee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shonpee.shonpee.repository.CartRepository;
import com.shonpee.shonpee.repository.OrderRepository;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.domain.OrderBean;
import com.shonpee.shonpee.domain.ProductBean;

@Controller
public class OrderController {

	@Autowired
	private ProductRepository productDao;
	@Autowired
	private CartRepository cartDao;
	@Autowired
	private OrderRepository orderDao;

	@RequestMapping(value = "/checkout")
	public String checkout(HttpSession session, Model model, String ordering, OrderBean OB) {
		System.out.println("------------checkout----------");
		System.out.println(session.getAttribute("checkoutCB"));
//		System.out.println("session資料="+session.getAttribute("checkoutCB"));
		ArrayList<CartBean> cartlist = (ArrayList<CartBean>) (session.getAttribute("checkoutCB"));
		List<ProductBean> Plist = productDao.findAll();
//		System.out.println(cartlist);
		model.addAttribute("cartlist", cartlist);
		int total = 0;
		for (int i = 0; i < cartlist.size(); i++) { // 總金額
			total += Integer.parseInt(cartlist.get(i).getTotalPrice());
		}
		int allquantity = cartlist.size();
//		System.out.println(allquantity);
		model.addAttribute("allquantity", allquantity);
		model.addAttribute("alltotal", total);
		System.out.println(allquantity);
		if (ordering != null) {
			System.out.println("----------order---------");
			// 增加productlist 判斷
			for (ProductBean productBean : Plist) {
				for (int j = 0; j < cartlist.size(); j++) {// 尋訪cart
					if (cartlist.get(j).getProductBean().getProductid().equals(productBean.getProductid())) {
						System.out.println("fffoorrrrrrrrrrrr");
						System.out.println(cartlist.get(j).getCartId());
						OB.setMemberId(cartlist.get(j).getMemberId());
						OB.setProductBean(productBean);
						OB.setOrderImg(cartlist.get(j).getCartPhoto());
						OB.setTypeValue(cartlist.get(j).getTypeValue1() + cartlist.get(j).getTypeValue2());
						Date OBtime = new Date();
//			      SimpleDateFormat OBtime = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
						OB.setOrderDate(OBtime);
						OB.setQuantity(Integer.parseInt(cartlist.get(j).getQuantity()));
						OB.setTotal((cartlist.get(j).getProductBean().getProductPrice())
								* (Integer.parseInt(cartlist.get(j).getQuantity())));
//				System.out.println((cartlist.get(j).getProductBean().getProduct_Price())*(Integer.parseInt(cartlist.get(j).getQuantity())));
						OB.setPayment(0);
						OB.setStatus(1);
						OB.setShippedDate(null);
						OB.setRequiredDate(null);
						orderDao.save(OB);
						cartDao.deleteById(cartlist.get(j).getCartId());
						int a = (Integer) session.getAttribute("cartsize");
						int cartsize = a - 1;
						session.setAttribute("cartsize", cartsize);
					}
				}
			}

			return "redirect:/main-page/shop-list0";
		}

		return "checkout";

	}

}
