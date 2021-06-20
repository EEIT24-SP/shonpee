package com.shonpee.shonpee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shonpee.shonpee.domain.Seller;

// @Repository : JpaRepository的套件裡已經設定好了@Repository，所以不用再註解
public interface SellerRepository extends JpaRepository<Seller, Integer>{

}


