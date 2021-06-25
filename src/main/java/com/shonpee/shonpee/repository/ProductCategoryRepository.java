package com.shonpee.shonpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shonpee.shonpee.domain.ProductCategoryBean;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryBean, Integer> {
	@Query(value="Select Category_name from productcategory where Category_id = ?1",nativeQuery = true)
    public String findCategoryNameByCategoryID (Integer data);
}
