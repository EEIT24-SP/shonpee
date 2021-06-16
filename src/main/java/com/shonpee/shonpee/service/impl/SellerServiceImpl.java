package com.shonpee.shonpee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shonpee.shonpee.entity.Seller;
import com.shonpee.shonpee.repository.SellerRepository;
import com.shonpee.shonpee.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService{

	@Autowired
	private SellerRepository sellerRepository;
	
	@Override
	public Seller findSellerById(long id) {
		return sellerRepository.findById(id);
	}
	
}
