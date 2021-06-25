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
	private String user_Account;
	private String password;
	private String member_Id;                   
	private String real_Name;
	private String email;
	private String tel;
	private String gender;
	private String year;
	private String month;
	private String day;
	private String address;
	
	
	@OneToMany(mappedBy = "memberBean")
	private List<ProductBean>  productlist;	
	
//	@OneToMany(mappedBy = "customerBean",fetch = FetchType.EAGER)
//	private List<Teproduct> list;
//	
//	@OneToMany(mappedBy = "customerBean",fetch = FetchType.EAGER)
//	private List<Profile> plist;
//	
}
