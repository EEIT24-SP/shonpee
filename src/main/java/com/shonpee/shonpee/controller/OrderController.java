package com.shonpee.shonpee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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

	
	// 賣家銷售頁
	@GetMapping("/seller-orders")
	public String showSellerOrders(Model model,HttpSession session) {
		String userName = (String)session.getAttribute("UserName");
		if(userName == null) {
			return "redirect:/login-page";
		}else{
			// 找出該賣家(此時登入者)的全部訂單
			///////// 未排序
			List<OrderBean> orderList = orderDao.findSellerOrderList(userName);
			model.addAttribute("orderList", orderList);
		}
		return "seller/MySales";
	}

	
	// 更新訂單狀態: 出貨(待出貨->待收貨)
	@PutMapping("/seller-order/{orderId}")
	public ResponseEntity<Map<String, Object>> shipoutOrder(@RequestBody OrderBean order) {
		Integer updatingOrderId = order.getOrderId();
		Integer currentStatus = order.getStatus();
		Optional<OrderBean> OptOrder = orderDao.findById(updatingOrderId);

		// 更新為"待收貨"
		if (OptOrder.isPresent() && currentStatus.equals(1)) {
			// 更新狀態為2(待收貨)，並以JPA存入資料庫
			OrderBean updatingOrder = OptOrder.get();
			updatingOrder.setStatus(2);
			OrderBean updatedOrder = orderDao.save(updatingOrder);
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
		Optional<OrderBean> OptOrder = orderDao.findById(cancelingOrderId);
		if (OptOrder.isPresent() && OptOrder.get().getStatus().equals(1)) {
			
			// 更新狀態為4(不成立)，並以JPA存入資料庫
			OrderBean cancelingOrder = OptOrder.get();
			cancelingOrder.setStatus(4);
			OrderBean canceledOrder = orderDao.save(cancelingOrder);
			//更新商品數量回原數量
			Integer productStock= canceledOrder.getProductBean().getProductStock()+canceledOrder.getQuantity();
			canceledOrder.getProductBean().setProductStock(productStock);;
			productDao.save(canceledOrder.getProductBean());
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
	

	@RequestMapping(value = "/checkout")
	public String checkout(HttpSession session, Model model, String ordering, OrderBean OB) {
		ArrayList<CartBean> cartlist = (ArrayList<CartBean>) (session.getAttribute("checkoutCB"));
		List<ProductBean> Plist = productDao.findAll();
		model.addAttribute("cartlist", cartlist);
		int total = 0;
		for (int i = 0; i < cartlist.size(); i++) { // 總金額
			total += Integer.parseInt(cartlist.get(i).getTotalPrice());
		}
		int allquantity = cartlist.size();
		model.addAttribute("allquantity", allquantity);
		model.addAttribute("alltotal", total);
		if (ordering != null) {
			// 增加productlist 判斷
			for (ProductBean productBean : Plist) {
				for (int j = 0; j < cartlist.size(); j++) {// 尋訪cart
					if (cartlist.get(j).getProductBean().getProductid().equals(productBean.getProductid())) {
						Integer productStock = productBean.getProductStock();
						//商品數量減去訂單數量 
						Integer newProductStock = (productStock-Integer.parseInt(cartlist.get(j).getQuantity()));
						//少於數量則不做
						if(newProductStock>=0) {
							//商品新數量更新
							productBean.setProductStock(newProductStock);
							//銷售數量更新-------------
							if(cartlist.get(j).getProductBean().getProductSell()==null) {
								cartlist.get(j).getProductBean().setProductSell(0);
								cartlist.get(j).getProductBean().setProductSell(productBean.getProductSell()+Integer.parseInt(cartlist.get(j).getQuantity()));
								productDao.save(productBean);
							}else {
								productBean.setProductSell(productBean.getProductSell()+Integer.parseInt(cartlist.get(j).getQuantity()));
								productDao.save(productBean);
							}
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
							OB.setPayment(0);
							OB.setStatus(1);
							OB.setShippedDate(null);
							OB.setRequiredDate(null);
							orderDao.save(OB);
							cartDao.deleteById(cartlist.get(j).getCartId());
							int a = (Integer) session.getAttribute("cartsize");
							int cartsize = a - 1;
							session.setAttribute("cartsize", cartsize);
						}else {
							return "redirect:/main-page/shop-list";
						}
					}
				}
			}

			return "redirect:/main-page/shop-list";
		}

		return "checkout";

	}

}
