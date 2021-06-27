
package com.shonpee.shonpee.domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class MemberBean {
	@Id
	private String userAccount;
	private String password;
	private Integer memberId;                   
	private String realName;
	private String email;
	private String tel;
	private String gender;
	private String year;
	private String month;
	private String day;
	private String address;
	private String memberPhoto;
	
	
	@OneToMany(mappedBy = "memberBean")
	private List<ProductBean>  productlist;	
	
}
