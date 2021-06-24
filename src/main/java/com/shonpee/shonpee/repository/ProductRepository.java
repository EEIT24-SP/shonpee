package com.shonpee.shonpee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.Productcategory;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<ProductBean, Integer> {

//	@Query("Select max(productid) from ProductBean")
//	Integer findmaxproductid();

	List<ProductBean> findByProductFirstCategoryId(Integer productFirstCategoryId);

}
