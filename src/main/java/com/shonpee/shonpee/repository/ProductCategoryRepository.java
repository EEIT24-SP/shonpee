package com.shonpee.shonpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shonpee.shonpee.domain.ProductCategoryBean;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryBean, Integer> {
	
}
