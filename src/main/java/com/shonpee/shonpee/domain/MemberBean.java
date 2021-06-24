package com.shonpee.shonpee.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class MemberBean {
	@Id
	private String user_Account;
	private String password;
	private Integer member_Id;                   
	private String real_Name;
	private String email;
	private String tel;
	private Integer gender;
	private String address;
	private String year;
	private String month;
	private String day;
	
//	@OneToMany(mappedBy = "customerBean",fetch = FetchType.EAGER)
//	private List<Teproduct> list;
//	
//	@OneToMany(mappedBy = "customerBean",fetch = FetchType.EAGER)
//	private List<Profile> plist;
//	
}
