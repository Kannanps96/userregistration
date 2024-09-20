package com.user.userregistration.registrationandauthcontroller.dto;

import lombok.Data;

@Data
public class UserRegistrationDetailsVO {
	private Long id;
	private String name;
	private String gender;
	private String password;
	private String email;
}