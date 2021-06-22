package com.shonpee.shonpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shonpee.shonpee.domain.PropertyBean;

public interface PropertyRepository extends JpaRepository<PropertyBean, Integer> {
	
}
