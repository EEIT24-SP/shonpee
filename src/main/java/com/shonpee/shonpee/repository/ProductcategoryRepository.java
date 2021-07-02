package com.shonpee.shonpee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.Productcategory;

public interface ProductcategoryRepository extends JpaRepository<Productcategory, Integer> {

	@Query(value="Select Category_name from productcategory where Category_id = ?1",nativeQuery = true)
    public String findCategoryNameByCategoryID (Integer data);
    public List<Productcategory> findByParentId(Integer parentId);
	
}
