package com.shonpee.shonpee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shonpee.shonpee.domain.OrderBean;

public interface OrderRepository extends JpaRepository<OrderBean, Integer>{
//	@Query(nativeQuery = true,value = "select password from member where password = :xxx")
//	String QueryAnnotationParams(@Param("xxx") String password);
	List<OrderBean> findByStatus(Integer status);
	List<OrderBean> findByOrderId(Integer orderId);
}
