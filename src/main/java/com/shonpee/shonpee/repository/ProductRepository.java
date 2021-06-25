package com.shonpee.shonpee.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shonpee.shonpee.domain.ProductBean;



public interface ProductRepository extends JpaRepository<ProductBean, String>,JpaSpecificationExecutor<ProductBean> {


}
