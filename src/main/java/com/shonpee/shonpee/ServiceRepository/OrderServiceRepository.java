package com.shonpee.shonpee.ServiceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.server.reactive.AbstractListenerReadPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shonpee.shonpee.repository.CartRepository;
import com.shonpee.shonpee.repository.OrderRepository;
import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.domain.ProductBean;

@Service
@Transactional
public class OrderServiceRepository {

	private OrderRepository orderDao = null;
	private CartRepository  cartDao = null;
	
//	public CartBean select(CartBean bean){
//		List<CartBean> result = null;
//		 if(bean!=null) {
//			 Optional<CartBean> optional = cartDao.findById(bean.getCart_id());
//			 if(!optional.isEmpty()) {//存在
//				 CartBean temp = optional.get();
//				 result = new ArrayList<CartBean>();
//				 result.add(temp);
//			 }else {
//				 result = null;
//			 }
//
//		 }
//		
//	}
//	
	
	

