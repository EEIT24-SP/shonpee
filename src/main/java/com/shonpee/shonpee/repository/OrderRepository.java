package com.shonpee.shonpee.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shonpee.shonpee.domain.OrderAndProductData;
import com.shonpee.shonpee.domain.OrderBean;

public interface OrderRepository extends JpaRepository<OrderBean, Integer> {
	//    @Query(nativeQuery = true,value = "select password from member where password = :xxx")
	//    String QueryAnnotationParams(@Param("xxx") String password);

	List<OrderBean> findByStatus(Integer status);
	
	List<OrderBean> findByOrderId(Integer orderId);
	
	// 從賣家(登入者)帳號取得的該賣家order清單
	@Query
	("SELECT o "
	+ "FROM OrderBean o "
	+ "WHERE o.productBean.memberBean.userAccount = :sellerId")
	List<OrderBean> findSellerOrderList(@Param("sellerId") String sellerUserAccount);
	
	// 查詢訂單狀態為?的產品
	// 一對一查詢，用constructor寫入
	@Query("SELECT new com.shonpee.shonpee.domain.OrderAndProductData(o, p) "
			+ "FROM OrderBean o, ProductBean p "
			+ "WHERE o.productBean.productid = p.productid "
			+ "AND p.memberBean.userAccount = :sellerId")
	List<OrderAndProductData> findOrderAndProductDatas(@Param("sellerId") String sellerUserAccount);
	
}
