package com.shonpee.shonpee.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "productTypeDetail")
@Data
public class PropertyBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Propertyid;
	private String PropertyName;
	private String PropertyValue;

	
	
	@ManyToOne
	@JoinColumn(name = "Productid1")
	private ProductBean productBean;
}
