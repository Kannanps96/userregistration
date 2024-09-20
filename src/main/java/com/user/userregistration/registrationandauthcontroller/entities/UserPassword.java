package com.user.userregistration.registrationandauthcontroller.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(schema = "userregistration", catalog = "userregistration", name = "user_password")
@Data
public class UserPassword {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "user_details_id")
	private Long userDetailsId;
	@Column(name = "password")
	private String password;

}