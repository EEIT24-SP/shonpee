package com.shonpee.shonpee.domain;

<<<<<<< HEAD
import java.util.List;

import javax.persistence.CascadeType;
=======

import java.util.List;

>>>>>>> origin/dev
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
<<<<<<< HEAD
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;


@Entity
@Table(name="product")
@Data
public class ProductBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productid;
	private String  productName;
	private Integer productPrice;
	private Integer productStock;
	private String  productPhoto;
	private String  productDetail;
	private Integer productFirstCategoryId;
	private Integer productSecondCategoryId;
	private Integer productThirdCategoryId;
	private String  memberId;

}
=======
import javax.persistence.Table;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Productid;
	private String productName;
	private Integer productPrice;
	private Integer productStock;
	private String productPhoto;
	private String productDetail;
	private Integer productFirstCategoryId;
	private Integer productSecondCategoryId;
	private Integer productThirdCategoryId;	
	
	@ManyToOne 
	@JoinColumn(name = "member_id")
	private MemberBean memberBean;
	

}



>>>>>>> origin/dev
