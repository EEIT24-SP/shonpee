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
@Table(name="productTypeDetailSecond")
@Data
public class  PropertyBeanSecond {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer propertyid;
	private String propertyName;
	private String propertyValue;
	private Integer productid2;
	

}
