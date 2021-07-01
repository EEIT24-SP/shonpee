package com.shonpee.shonpee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.domain.OrderBean;
import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.Productcategory;
import com.shonpee.shonpee.repository.CartRepository;
import com.shonpee.shonpee.repository.OrderRepository;
import com.shonpee.shonpee.repository.ProductRepository;

@Controller
public class PublicController {

	@Autowired
	CartRepository CR;
	@Autowired
	OrderRepository OR;
	@Autowired
	ProductRepository PR;

	@RequestMapping("/main-page/card")
	public String card() {
		return "account/credit_card";
	}

	@RequestMapping("/main-page/address")
	public String address() {
		return "account/address";
	}

	@RequestMapping("/main-page/password")
	public String password() {
		return "account/password";
	}

	@RequestMapping("/main-page/profile")
	public String profile() {
		return "account/profile";
	}

	@RequestMapping("main-page/shop-list")
	public String shoplist(Model model) {
		String[] abc = { "待付款", "待出貨", "待收貨", "完成", "不成立", "全部" };
		model.addAttribute("abc", abc);
		return "redirect:/main-page/shop-list5";
	}

	@GetMapping("main-page/shop-list{index}")
	public String ddd(@PathVariable("index") int index, Model model, HttpSession session) {
		String[] abc = { "待付款", "待出貨", "待收貨", "完成", "不成立", "全部" };
		Object Name = session.getAttribute("UserName");
		model.addAttribute("abc", abc);
		model.addAttribute("Index", index);
		ArrayList<OrderBean> arrOrder = new ArrayList<OrderBean>();
		List<OrderBean> list = OR.findByStatus(index);
		List<OrderBean> listAll = OR.findAll();
		if (index == 5) {
			for (OrderBean orderBean : listAll) {
				if (orderBean.getMemberId().equals(Name)) {
					arrOrder.add(orderBean);
					model.addAttribute("arrOrder", arrOrder);
					System.out.println("全部頁面成功");
				}
			}
			return "shop_list/shop_list";
		}
		for (OrderBean orderBean : list) {
			if (orderBean.getMemberId().equals(Name)) {
				arrOrder.add(orderBean);
				model.addAttribute("arrOrder", arrOrder);
				System.out.println("成功");
			}
		}
		System.out.println(index);
		return "shop_list/shop_list";
	}

	@PostMapping(value = ("main-page/shop-list{index}"))
	public String changeType(@PathVariable("index") int index, @RequestParam(value = "changeType") Integer changeType,
			OrderBean OB, CartBean CB, HttpSession session) {
		Object Name = session.getAttribute("UserName");
		System.out.println("我是" + index);
		System.out.println("轉向成功");
		System.out.println(OB.getQuantity());
		List<OrderBean> list = OR.findByOrderId(OB.getOrderId());
		List<OrderBean> listBA = OR.findAll();
		List<ProductBean> PDlist = PR.findAll();
		// 99代表再次購買代碼
		if (changeType == 99) {
			for (OrderBean orderBean : listBA) {
				for (ProductBean productBean : PDlist) {
					System.out.println("進來了");
					if (productBean.getProductid().equals(OB.getProductBean().getProductid()) && productBean.getProductStatus()==null) {
						System.out.println("進來了1");
						String[] split = productBean.getProductPhoto().split(",");
						String[] splitTypeValue = orderBean.getTypeValue().split(",");
						CB.setCartPhoto(split[0]); 
						CB.setTotalPrice(Integer.toString(OB.getTotal()));
						CB.setMemberId(Name.toString());
						CB.setQuantity(Integer.toString(OB.getQuantity()));
						CB.setProductBean(productBean);
						if (splitTypeValue.length > 1) { 
							CB.setTypeValue1(splitTypeValue[0]);
							CB.setTypeValue2(splitTypeValue[1]);
						}
						CR.save(CB);
						int a = (Integer) session.getAttribute("cartsize");
						int cartsize = a +1;
						session.setAttribute("cartsize", cartsize);
						System.out.println("準備再次購買" + productBean.getProductid());
						return "redirect:/main-page/cart";
					}
				}if(OB.getProductBean().getProductStatus() == null) {
					System.out.println("商品已下架");
					return "redirect:/main-page/shop-list" + index;
				}
			}

		}
// 不符合重新購買的 單純改變狀態
		for (OrderBean orderBean : list) {
			orderBean.setStatus(changeType);
			OR.save(orderBean);
			System.out.println("更改成功" + orderBean.getStatus());
		}

		return "redirect:/main-page/shop-list" + index;
	}

}