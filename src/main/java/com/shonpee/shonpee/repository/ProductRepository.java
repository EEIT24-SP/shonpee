package com.shonpee.shonpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shonpee.shonpee.domain.ProductBean;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<ProductBean, Integer> {
	
	@Query("Select max(Productid) from ProductBean")
	Integer findmaxproductid();
	
}
