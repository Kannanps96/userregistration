package com.user.userregistration.crudservice;

import java.util.List;

import com.user.userregistration.registrationandauthcontroller.dto.UserRegistrationDetailsVO;

public interface CrudService {

	List<UserRegistrationDetailsVO> findAllRegisteredUsers();

	void deleteUserByEmail(String email);
	
	
	
}
