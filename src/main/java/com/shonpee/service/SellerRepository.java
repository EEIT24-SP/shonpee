package com.shonpee.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shonpee.domain.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer>{

}
