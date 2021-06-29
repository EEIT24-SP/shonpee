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
	public String checkout(HttpSession session, Model model,String ordering,OrderBean OB) {
		System.out.println("------------checkout----------");
		System.out.println(session.getAttribute("checkoutCB"));
//		System.out.println("session資料="+session.getAttribute("checkoutCB"));
		ArrayList<CartBean> cartlist = (ArrayList<CartBean>)(session.getAttribute("checkoutCB"));
		List<ProductBean>Plist = productDao.findAll();
//		System.out.println(cartlist);
		model.addAttribute("cartlist",cartlist);
		int total=0;
		for(int i=0;i<cartlist.size();i++) { //總金額
			total += Integer.parseInt(cartlist.get(i).getTotalPrice());
		}
		int allquantity =  cartlist.size();
//		System.out.println(allquantity);
		model.addAttribute("allquantity", allquantity);
		model.addAttribute("alltotal", total);
		System.out.println(allquantity);
		if(ordering!=null) {
			System.out.println("----------order---------");
			//增加productlist 判斷
		for(ProductBean productBean:Plist) {	
			for(int j=0;j<cartlist.size();j++){//尋訪cart
				if(cartlist.get(j).getProductBean().getProductid().equals(productBean.getProductid())) {
				System.out.println("fffoorrrrrrrrrrrr");
				System.out.println(cartlist.get(j).getCartId());
				OB = new OrderBean();
				OB.setMemberId(cartlist.get(j).getProductBean().getMemberBean().getUserAccount());
				OB.setProductBean(productBean);
				OB.setOrderImg(cartlist.get(j).getCartPhoto());
				OB.setTypeValue(cartlist.get(j).getTypeValue1()+cartlist.get(j).getTypeValue2());
			      Date OBtime = new Date( );
//			      SimpleDateFormat OBtime = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
				OB.setOrderDate(OBtime);
				OB.setQuantity(Integer.parseInt(cartlist.get(j).getQuantity()));
				OB.setTotal((cartlist.get(j).getProductBean().getProductPrice())*(Integer.parseInt(cartlist.get(j).getQuantity())));
//				System.out.println((cartlist.get(j).getProductBean().getProduct_Price())*(Integer.parseInt(cartlist.get(j).getQuantity())));
				OB.setPayment(0);
				OB.setStatus(0); 
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
	
	
		
		
		
		

		
//		list.get(0).getProductBean().getMemberBean().getUser_Account();//賣家帳號
//		list.getClass().getCart_Photo();//照片
//		list.get(0).getProductBean().getProduct_Name();//商品名稱
//		list.get(0).getProductBean().getProduct_Third_Category_Id();//規格
//		list.get(0).getProductBean().getProduct_Price();//價錢
//		list.get(0).getQuantity();//數量
//		list.get(0).getTotal_Price();
		
		
//		System.out.println("比對資料="s+cartDao.findById((int)session.getAttribute("checkoutCB")));
//		Optional<CartBean> option = cartDao.findById((int) session.getAttribute("checkoutCB"));
//		System.out.println(option.get());
//		CartBean CB = option.get();
//
//		Optional<CartBean> option = cartDao.findById(1);
//		System.out.println(productDao.findById(option.get().getProduct_id()).get().getProduct_name());

//			 Optional<CartBean> option = cartDao.findById(bean.getCart_id());
//		if (!option.isEmpty()) {// 存在
//		ArrayList<String> ilist = new ArrayList<String>();
//for(int i=0;i<cartlist.size();i++) {
////	System.out.println("seller"+Integer.toString(i));
//////	System.out.println(list.get(i));
//////	CartBean CBlist = list.get(i);
//			model.addAttribute("seller", cartlist.get(i).getProductBean().getMemberBean().getUser_Account());
//////		System.out.println("cccccc = "+option.get().getProductBean().getProduct_Photo());
//			model.addAttribute("img", cartlist.get(i).getCart_Photo());
//////		System.out.println(productDao.findById(option.get().getProduct_id()).get().getProduct_photo());
//			model.addAttribute("product", cartlist.get(i).getProductBean().getProduct_Name());
//			model.addAttribute("type", cartlist.get(i).getProductBean().getProduct_Third_Category_Id());
//			model.addAttribute("unitprice", cartlist.get(i).getProductBean().getProduct_Price());
//			model.addAttribute("quantity", cartlist.get(i).getQuantity());
//			model.addAttribute("productprice",(cartlist.get(i).getProductBean().getProduct_Price())*Integer.parseInt(cartlist.get(0).getQuantity()));
////////				 	return "redirect:/test3";
//////		}
////
////			ilist.add(i,Integer.toString(i));
////			System.out.println();
////
//}
//model.addAttribute("ilist", ilist);
//System.out.println(ilist);
//
////		System.out.println();
////		return null;
//
//	// 假資料測試
//	List<List<ProductBean>> cartlist = new ArrayList<List<ProductBean>>();
//	List<ProductBean> sellerlist = new ArrayList<ProductBean>();
//	ProductBean product = new ProductBean();
//	for(
//	int i = 0;i<3;i++){
//		cartlist.add(sellerlist);
//		for (int j = 0; j < 2; j++) {
////				product.setMember_id("toom");
////				product.setProduct_name("rrrrrrgogogoo");
//			sellerlist.add(product);
//		}
//	}

}
