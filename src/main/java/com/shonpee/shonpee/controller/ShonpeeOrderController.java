package com.shonpee.shonpee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shonpee.shonpee.domain.OrderAndProductData;
import com.shonpee.shonpee.domain.ShonpeeOrderBean;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.repository.ShonpeeOrderRepository;

@Controller
public class ShonpeeOrderController {

	// 待出貨
	Integer statusReadyToShip = new Integer(1);
	// 待收貨
	Integer statusReadyToPick = new Integer(2);
	
	@Autowired
	private ShonpeeOrderRepository shonpeeOrderRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/mysales")
	public String showSellerOrders(Model model) {
		// Status=1,表示待出貨狀態
		Integer statusNum = new Integer(1);
		
		// 找出待出貨的訂單
		// 方法1:參考一對一查詢
		List<OrderAndProductData> status1Orders = shonpeeOrderRepository.findOrderAndProductDatas(statusNum);
		model.addAttribute("status1Orders", status1Orders);
		return "seller/MySales";
	}
	
	// 更新訂單狀態: 出貨(待出貨->待收貨)
	@PutMapping("/orderStatus/update")
	@ResponseBody
	public String updateTheOrder(@RequestBody ShonpeeOrderBean order) {
		Integer updatingOrderId = order.getOrderId();
		Integer currentStatus = order.getStatus();
		Optional<ShonpeeOrderBean> OptOrder = shonpeeOrderRepository.findById(updatingOrderId);
		if(OptOrder.isPresent() && currentStatus.equals(statusReadyToShip)) {
			ShonpeeOrderBean updatingOrder = OptOrder.get();
			updatingOrder.setStatus(statusReadyToPick);
			ShonpeeOrderBean updatedOrder = shonpeeOrderRepository.save(updatingOrder);
			
			return String.valueOf(updatedOrder.getOrderId());			
		}else {
			// 可能要用@Transaction綁所有更新完成的動作，驗證是否有更新成功
		}
		return "Error";
	}
	
}
