package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@Table(name="tm_user")
@ToString
public class TmUser {

	@Id
	private String uid;
	
	@Column(nullable = false)
	private String pwd;
	
	private String username;
	
	private String addr;
	
	private String email;
	
	private int mobile_number;
	
	private String useCheck;
	
	private String roles;
}
