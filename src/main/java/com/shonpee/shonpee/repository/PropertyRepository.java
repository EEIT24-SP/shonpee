package com.shonpee.shonpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shonpee.shonpee.domain.PropertyBean;

public interface PropertyRepository extends JpaRepository<PropertyBean, Integer> {
	
	
	@Query(value="Select * from product_type_detail where Productid1 = ?1",nativeQuery = true)
	public PropertyBean findPropertyBeanByName (Integer data);
}
