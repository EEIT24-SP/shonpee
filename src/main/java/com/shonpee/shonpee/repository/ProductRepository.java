package com.shonpee.shonpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shonpee.shonpee.domain.ProductBean;

public interface ProductRepository extends JpaRepository<ProductBean, Integer> {
}