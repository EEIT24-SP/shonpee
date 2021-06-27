package com.shonpee.shonpee.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shonpee.shonpee.domain.OrderAndProductData;
import com.shonpee.shonpee.domain.ShonpeeOrderBean;

public interface ShonpeeOrderRepository extends JpaRepository<ShonpeeOrderBean, Integer> {
	List<ShonpeeOrderBean> findByStatus(Integer status);
	
	// 查詢訂單狀態為?的產品
	// 一對一查詢，用constructor寫入
	@Query("SELECT new com.shonpee.shonpee.domain.OrderAndProductData(so, p) "
			+ "FROM ShonpeeOrderBean so, ProductBean p "
			+ "WHERE so.productId = p.productid "
			+ "AND so.status = :statusNum")
	List<OrderAndProductData> findOrderAndProductDatas(@Param("statusNum") Integer statusNum);
	
}
