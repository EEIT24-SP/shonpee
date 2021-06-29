package com.shonpee.shonpee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.shonpee.shonpee.domain.ShonpeeOrderBean;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.repository.ShonpeeOrderRepository;

@Controller
public class ShonpeeOrderController {

	@Autowired
	private ShonpeeOrderRepository shonpeeOrderRepository;

	@Autowired
	private ProductRepository productRepository;

	// 賣家銷售頁
	@GetMapping("/mysales")
	public String showSellerOrders(Model model) {
		// 找出全部訂單
		///////// 未排序
		List<OrderAndProductData> ordersWithProduct = shonpeeOrderRepository.findOrderAndProductDatas();
		model.addAttribute("ordersWithProduct", ordersWithProduct);
		return "seller/MySales";
	}

	// 更新訂單狀態: 出貨(待出貨->待收貨)
	@PutMapping("/order/{id}")
	public ResponseEntity<Map<String, Object>> shipoutOrder(@RequestBody ShonpeeOrderBean order) {
		Integer updatingOrderId = order.getOrderId();
		String currentStatus = order.getStatus();
		Optional<ShonpeeOrderBean> OptOrder = shonpeeOrderRepository.findById(updatingOrderId);

		// 更新為"待收貨"
		if (OptOrder.isPresent() && currentStatus.equals("待出貨")) {
			// 以JPA存入資料庫
			ShonpeeOrderBean updatingOrder = OptOrder.get();
			updatingOrder.setStatus("待收貨");
			ShonpeeOrderBean updatedOrder = shonpeeOrderRepository.save(updatingOrder);
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

	// 更新訂單狀態: 取消(待出貨->取消)
	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable("orderId") Integer cancelingOrderId) {
		Optional<ShonpeeOrderBean> OptOrder = shonpeeOrderRepository.findById(cancelingOrderId);
		if (OptOrder.isPresent() && OptOrder.get().getStatus().equals("待出貨")) {
			// 以JPA存入資料庫
			ShonpeeOrderBean cancelingOrder = OptOrder.get();
			cancelingOrder.setStatus("取消");
			ShonpeeOrderBean canceledOrder = shonpeeOrderRepository.save(cancelingOrder);
			// 產生ResponseEntity，內裝Map，傳回Response和更新後資料
			Map<String, Object> canceledOrderDataMap = new HashMap<String, Object>();
			canceledOrderDataMap.put("orderId", canceledOrder.getOrderId());
			canceledOrderDataMap.put("status", canceledOrder.getStatus());
			System.out.println("----------------------");
			System.out.println("canceledID => " + canceledOrder.getOrderId());
			System.out.println("canceledStatus => " + canceledOrder.getStatus());
			System.out.println("----------------------");
//
			return new ResponseEntity(canceledOrderDataMap, HttpStatus.OK);
		} else {
			// 可能要用@Transaction綁所有更新完成的動作，驗證是否有更新成功
			System.out.println("----------------------");
			System.out.println("...ELSE...");
			System.out.println("----------------------");
		}
		System.out.println("----------------------");
		System.out.println("RETURN NULL!!!");
		System.out.println("----------------------");
		return null;

	}
}
