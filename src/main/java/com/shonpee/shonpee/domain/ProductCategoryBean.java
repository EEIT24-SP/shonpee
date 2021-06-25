package com.shonpee.shonpee.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="productcategory")
@Data
public class ProductCategoryBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer CategoryId;
	private String CategoryName;
	private Integer ParentId;
	private String Type;
	private String CategoryIcon;
	
}
