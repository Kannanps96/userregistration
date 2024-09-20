package com.user.userregistration.conflogin.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.user.userregistration.registrationandauthcontroller.dto.UserRegistrationDetailsVO;


public interface CustomLoginService extends UserDetailsService {

	UserRegistrationDetailsVO findUserByEmail(String username);




}