package com.shonpee.shonpee.repositoryservice;

import java.util.Optional;

import com.shonpee.shonpee.domain.Seller;

public interface SellerService {

	Integer getMyMemberID();

	Seller findMeAsSeller() throws Exception;

	Seller updateMyShopData(Seller seller) throws Exception;
	
}
