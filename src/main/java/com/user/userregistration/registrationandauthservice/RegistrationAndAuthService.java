package com.user.userregistration.registrationandauthservice;

import com.user.userregistration.registrationandauthcontroller.dto.UserRegistrationDetailsVO;

public interface RegistrationAndAuthService {

	String saveUserDetails(UserRegistrationDetailsVO userDetailVO);
	
	
	
}
