package com.shonpee.shonpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shonpee.shonpee.domain.OrderBean;

public interface OrderRepository extends JpaRepository<OrderBean, Integer>{

}
