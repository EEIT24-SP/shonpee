package com.shonpee.shonpee.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.domain.MemberBean;



public interface CartRepository extends JpaRepository<CartBean, Integer>,JpaSpecificationExecutor<CartBean> {


}
