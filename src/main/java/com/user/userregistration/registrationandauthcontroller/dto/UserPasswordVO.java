package com.user.userregistration.registrationandauthcontroller.dto;

import lombok.Data;

@Data
public class UserPasswordVO {
	private Long id;
	private Long userDetailsId;
	private String password;
}