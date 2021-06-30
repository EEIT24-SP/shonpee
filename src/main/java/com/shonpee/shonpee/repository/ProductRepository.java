package com.shonpee.shonpee.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shonpee.shonpee.domain.ProductBean;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<ProductBean, Integer> {
	
	

	@Query(value="Select * from product where Member_id = ?1",nativeQuery = true)
    public List<ProductBean> findProductBeanbyMember(String data);





}
