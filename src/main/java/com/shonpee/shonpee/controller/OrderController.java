package com.shonpee.shonpee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shonpee.shonpee.Repository.CartRepository;
import com.shonpee.shonpee.Repository.ProductRepository;
import com.shonpee.shonpee.ServiceRepository.OrderServiceRepository;
import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.domain.OrderBean;
import com.shonpee.shonpee.domain.ProductBean;

@Controller
public class OrderController {
	@Autowired
	private OrderServiceRepository orderService;
	@Autowired
	private ProductRepository productDao;
	@Autowired
	private CartRepository cartDao;

	@RequestMapping(value = "/checkout")
	public void checkout(CartBean bean, Model model) {

		Optional<CartBean> option = cartDao.findById(1);
//		System.out.println(productDao.findById(option.get().getProduct_id()).get().getProduct_name());

//		 if(bean!=null) {
//			 Optional<CartBean> option = cartDao.findById(bean.getCart_id());
//			 if(!option.isEmpty()) {//存在

		model.addAttribute("seller", option.get().getMember_Id());
//		System.out.println("cccccc = "+option.get().getProductBean().getProduct_Photo());
		model.addAttribute("img", option.get().getProductBean().getProduct_Photo());
//		System.out.println(productDao.findById(option.get().getProduct_id()).get().getProduct_photo());
		model.addAttribute("product", option.get().getProductBean().getProduct_Name());
		model.addAttribute("type", option.get().getProductBean().getProduct_Third_Category_Id());
		model.addAttribute("unitprice", option.get().getProductBean().getProduct_Price());
		model.addAttribute("quantity", option.get().getQuantity());
		model.addAttribute("totalprice", option.get().getQuantity());
//				 	return "redirect:/test3";
//	}

//		System.out.println();
//		return null;

	// 假資料測試
	List<List<ProductBean>> cartlist = new ArrayList<List<ProductBean>>();
	List<ProductBean> sellerlist = new ArrayList<ProductBean>();
	ProductBean product = new ProductBean();
	for(
	int i = 0;i<3;i++){
		cartlist.add(sellerlist);
		for (int j = 0; j < 2; j++) {
//				product.setMember_id("toom");
//				product.setProduct_name("rrrrrrgogogoo");
			sellerlist.add(product);
		}
	}model.addAttribute("cartlist",cartlist);model.addAttribute("sellerlist",sellerlist);model.addAttribute("product",product);System.out.println(sellerlist);

//		Optional<CartBean> option = cartDao.findById(1);
////		System.out.println(productDao.findById(option.get().getProduct_id()).get().getProduct_name());
//		
////		 if(bean!=null) {
////			 Optional<CartBean> option = cartDao.findById(bean.getCart_id());
//			 if(!option.isEmpty()) {//存在
//				 
//					model.addAttribute("seller",option.get().getMember_id());
//					model.addAttribute("img", productDao.findById(option.get().getProduct_id()).get().getProduct_photo());
////					System.out.println(productDao.findById(option.get().getProduct_id()).get().getProduct_photo());
//					model.addAttribute("product",productDao.findById(option.get().getProduct_id()).get().getProduct_name());
//					model.addAttribute("type", productDao.findById(option.get().getProduct_id()).get().getProduct_third_category_id());
//					model.addAttribute("unitprice", productDao.findById(option.get().getProduct_id()).get().getProduct_price());
//					model.addAttribute("quantity", option.get().getQuantity());
//					model.addAttribute("totalprice",(productDao.findById(option.get().getProduct_id()).get().getProduct_price())*(option.get().getQuantity()));
//			 }else {
////				 	return "redirect:/test3";
//			 }
//
//		
//		System.out.println();
////		return null;
}

}
