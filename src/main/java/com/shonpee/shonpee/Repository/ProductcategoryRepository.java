package com.shonpee.shonpee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.Productcategory;

public interface ProductcategoryRepository extends JpaRepository<Productcategory, Integer> {

	List<Productcategory> findByParentId(Integer parentId);
	
}
