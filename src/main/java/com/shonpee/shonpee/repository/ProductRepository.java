package com.shonpee.shonpee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.domain.ProductBean;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<ProductBean, Integer>,JpaSpecificationExecutor<ProductBean> {
	
//    @Query("Select max(Productid) from ProductBean")
//    public Integer findmaxproductid();

//    @Query(value="Select p from ProductBean p where p.Productid = ?1")
//    public ProductBean findProductBeanbyID(Integer data);

    @Query(value="Select * from product where Member_id = ?1",nativeQuery = true)
    public List<ProductBean> findProductBeanbyMember(String data);

    @Query(value="Select * from product where Product_name LIKE %?1% ",nativeQuery = true)
    public List<ProductBean> findProductBeanbyName(String data);
    
    List<ProductBean> findByProductFirstCategoryId(Integer productFirstCategoryId);
    
    
}