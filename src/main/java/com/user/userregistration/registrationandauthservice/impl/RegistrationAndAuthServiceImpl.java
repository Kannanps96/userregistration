package com.user.userregistration.registrationandauthservice.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.userregistration.registrationandauthcontroller.dto.UserRegistrationDetailsVO;
import com.user.userregistration.registrationandauthcontroller.entities.UserPassword;
import com.user.userregistration.registrationandauthcontroller.entities.UserRegistrationDetails;
import com.user.userregistration.registrationandauthcontroller.repo.UserPasswordRepo;
import com.user.userregistration.registrationandauthcontroller.repo.UserRegistrationDetailsRepo;
import com.user.userregistration.registrationandauthservice.RegistrationAndAuthService;

@Service
public class RegistrationAndAuthServiceImpl implements RegistrationAndAuthService {
	
	
	    private final ModelMapper modelMapper;
		
		@Autowired
	    public RegistrationAndAuthServiceImpl(ModelMapper modelMapper) {
	        this.modelMapper = modelMapper;
	    }
		
		
		@Autowired
		UserRegistrationDetailsRepo  userDetailsRepository;
		
		
		@Autowired
		UserPasswordRepo  userPasswordRepo;

	@Override
	@Transactional
	public String saveUserDetails(UserRegistrationDetailsVO userDetailVO) {
		String result;
		if (userDetailsRepository.findByEmail(userDetailVO.getEmail()).isPresent()) {
	            return "Email is already Registered";
	    }else {
	    	UserRegistrationDetails userDetail = modelMapper.map(userDetailVO,UserRegistrationDetails.class);
			userDetailsRepository.save(userDetail);
			UserPassword userPass=new UserPassword();
			userPass.setPassword(new BCryptPasswordEncoder().encode(userDetailVO.getPassword()));
			userPass.setUserDetailsId(userDetail.getId());
			userPasswordRepo.save(userPass);
			result="Y";
	    }
		return result;
	}



	
	
	
}
