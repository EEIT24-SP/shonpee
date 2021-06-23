package com.shonpee.shonpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shonpee.shonpee.domain.ShonpeeOrderBean;

public interface ShonpeeOrderRepository extends JpaRepository<ShonpeeOrderBean, Integer> {

}
