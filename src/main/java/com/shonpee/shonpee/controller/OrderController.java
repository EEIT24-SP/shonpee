package com.shonpee.shonpee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shonpee.shonpee.domain.OrderAndProductData;
import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.OrderBean;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.repository.OrderRepository;

@Controller
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	// 賣家銷售頁
	@GetMapping("/seller-orders")
	public String showSellerOrders(Model model,HttpSession session) {
		String userName = (String)session.getAttribute("UserName");
		if(userName == null) {
			return "redirect:/login-page";
		}else{
			// 找出該賣家(此時登入者)的全部訂單
			///////// 未排序
			List<OrderBean> orderList = orderRepository.findSellerOrderList(userName);
			model.addAttribute("orderList", orderList);
		}
		return "seller/MySales";
	}

	
	// 更新訂單狀態: 出貨(待出貨->待收貨)
	@PutMapping("/seller-order/{orderId}")
	public ResponseEntity<Map<String, Object>> shipoutOrder(@RequestBody OrderBean order) {
		Integer updatingOrderId = order.getOrderId();
		Integer currentStatus = order.getStatus();
		Optional<OrderBean> OptOrder = orderRepository.findById(updatingOrderId);

		// 更新為"待收貨"
		if (OptOrder.isPresent() && currentStatus.equals(1)) {
			// 更新狀態為2(待收貨)，並以JPA存入資料庫
			OrderBean updatingOrder = OptOrder.get();
			updatingOrder.setStatus(2);
			OrderBean updatedOrder = orderRepository.save(updatingOrder);
			// 產生ResponseEntity，內裝Map，傳回Response和更新後資料
			Map<String, Object> updatedOrderDataMap = new HashMap<String, Object>();
			updatedOrderDataMap.put("orderId", updatedOrder.getOrderId());
			updatedOrderDataMap.put("status", updatedOrder.getStatus());

			return new ResponseEntity(updatedOrderDataMap, HttpStatus.OK);
		} else {
			// 可能要用@Transaction綁所有更新完成的動作，驗證是否有更新成功
		}

		return null;
	}

	
	// 更新訂單狀態: 取消(待出貨->不成立)
	@DeleteMapping("/seller-order/{orderId}")
	public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable("orderId") Integer cancelingOrderId) {
		System.out.println("------------");
		System.out.println("cancelingOrderId" + cancelingOrderId);
		System.out.println("------------");
		Optional<OrderBean> OptOrder = orderRepository.findById(cancelingOrderId);
		if (OptOrder.isPresent() && OptOrder.get().getStatus().equals(1)) {
			// 更新狀態為4(不成立)，並以JPA存入資料庫
			OrderBean cancelingOrder = OptOrder.get();
			cancelingOrder.setStatus(4);
			OrderBean canceledOrder = orderRepository.save(cancelingOrder);
			// 產生ResponseEntity，內裝Map，傳回Response和更新後資料
			Map<String, Object> canceledOrderDataMap = new HashMap<String, Object>();
			canceledOrderDataMap.put("orderId", canceledOrder.getOrderId());
			canceledOrderDataMap.put("status", canceledOrder.getStatus());
			
			return new ResponseEntity(canceledOrderDataMap, HttpStatus.OK);
		} else {
			// 可能要用@Transaction綁所有更新完成的動作，驗證是否有更新成功
		}
		
		return null;
	}
}
