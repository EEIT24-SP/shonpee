package com.shonpee.shonpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shonpee.shonpee.domain.PropertyBean;
import com.shonpee.shonpee.domain.PropertyBeanSecond;

public interface PropertySecondRepository extends JpaRepository<PropertyBeanSecond, Integer> {
	
}
