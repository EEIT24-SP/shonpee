package com.shonpee.shonpee.repositoryservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.shonpee.shonpee.domain.Seller;
import com.shonpee.shonpee.repository.SellerRepository;

@Service
public class SellerServiceRepository implements SellerService{

	@Autowired
	private SellerRepository sellerRepository;

	
	@Override
	public Integer getMyMemberID() {
		Integer myMemberID = 1;
		return myMemberID;
	}
	
	@Override
	public Seller findMeAsSeller() throws Exception {
		Integer myMemberID = this.getMyMemberID();
		Optional<Seller> sellerOpt = sellerRepository.findById(myMemberID);
		Seller meSeller = sellerOpt.orElseThrow(() -> new Exception("查無此賣家Id"));
		return meSeller;
	}

	@Override
	public Seller updateMyShopData(Seller newSeller) throws Exception {
		Seller existingSeller = this.findMeAsSeller();
		
		existingSeller.setShopName(newSeller.getShopName());
		existingSeller.setShopIntro(newSeller.getShopIntro());
		sellerRepository.save(existingSeller);
		
		return existingSeller;
	}
	
}
