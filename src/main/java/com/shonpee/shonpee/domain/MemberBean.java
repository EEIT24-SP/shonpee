package com.shonpee.shonpee.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;

import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class MemberBean {
	
	
	@Id
	private String User_account;
	private String Password;
	private int Member_id;
	private String Real_name;
	private	String Email;
	private String Tel;
	private Date Birthday;
	private int Gender;
	private String Address;
}
