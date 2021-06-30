package com.shonpee.shonpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shonpee.shonpee.domain.PropertyBeanSecond;

<<<<<<< HEAD
public interface PropertySecondRepository extends JpaRepository<PropertyBeanSecond, Integer> {
	@Query(value="Select * from product_type_detail_second where Productid2 = ?1",nativeQuery = true)
    public PropertyBeanSecond findPropertyBeanByProdcutID (Integer data);
=======

public interface PropertySecondRepository extends JpaRepository<PropertyBeanSecond, Integer> {
	
	@Query(value="Select * from product_type_detail_second where Productid2 = ?1",nativeQuery = true)
	public PropertyBeanSecond findPropertyBeanByName (Integer data);
	
>>>>>>> origin/dev
}