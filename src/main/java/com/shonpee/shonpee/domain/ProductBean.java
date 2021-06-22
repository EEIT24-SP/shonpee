package com.shonpee.shonpee.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;


@Entity
@Table(name="product")
@Data
public class ProductBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Productid;
	private String ProductName;
	private Integer ProductPrice;
	private Integer ProductStock;
	private String ProductPhoto;
	private String ProductDetail;
	private String MemberId;
	private Integer ProductFirstCategoryId;
	private Integer ProductSecondCategoryId;
	private Integer ProductThirdCategoryId;
	
//	@OneToMany(
//			mappedBy = "PropertyBeanScond",
//			cascade = {CascadeType.REMOVE})
//	private List<PropertyBean> propertyBeans;
	
}
