package com.shonpee.shonpee.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shonpee.shonpee.domain.ProductBean;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<ProductBean, Integer> {
	
	

	@Query(value="Select * from product where Member_id = ?1",nativeQuery = true)
    public List<ProductBean> findProductBeanbyMember(String data);
=======

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.shonpee.shonpee.domain.Productcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shonpee.shonpee.domain.ProductBean;



public interface ProductRepository extends JpaRepository<ProductBean, String>,JpaSpecificationExecutor<ProductBean> {



	
	@Query("Select max(Productid) from ProductBean")
	public Integer findmaxproductid();
	
	@Query(value="Select p from ProductBean p where p.Productid = ?1")
	public ProductBean findProductBeanbyID(Integer data);

	List<ProductBean> findByProductFirstCategoryId(Integer productFirstCategoryId);
>>>>>>> origin/dev

}
