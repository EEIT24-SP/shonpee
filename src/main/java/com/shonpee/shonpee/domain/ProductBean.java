package com.shonpee.shonpee.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Integer Productid;
	private String  ProductName;
	private Integer ProductPrice;
	private Integer ProductStock;
	private String  ProductPhoto;
	private String  ProductDetail;
	private Integer ProductFirstCategoryId;
	private Integer ProductSecondCategoryId;
	private Integer ProductThirdCategoryId;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="MemberId")	
	private String  MemberId;
//	
}
