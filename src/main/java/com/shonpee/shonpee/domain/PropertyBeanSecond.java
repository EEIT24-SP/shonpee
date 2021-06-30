package com.shonpee.shonpee.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD

=======
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
>>>>>>> origin/dev
import javax.persistence.Table;

import lombok.Data;

@Entity
<<<<<<< HEAD
@Table(name="productTypeDetailSecond")
@Data
public class  PropertyBeanSecond {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer propertyid;
	private String propertyName;
	private String propertyValue;
	private Integer productid2;
	
=======
@Table(name = "productTypeDetailSecond")
@Data
public class PropertyBeanSecond {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Propertyid;
	private String PropertyName;
	private String PropertyValue;

//	@OneToOne
//	@JoinColumn(name="Productid2" ,
//	referencedColumnName="Productid")
	private Integer Productid2;

>>>>>>> origin/dev
}
