package com.user.userregistration.crudservice.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.userregistration.crudservice.CrudService;
import com.user.userregistration.registrationandauthcontroller.dto.UserRegistrationDetailsVO;
import com.user.userregistration.registrationandauthcontroller.entities.UserRegistrationDetails;
import com.user.userregistration.registrationandauthcontroller.repo.UserRegistrationDetailsRepo;


@Service
public class CrudServiceImpl implements CrudService {
	
	
	private final ModelMapper modelMapper;
	
	@Autowired
    public CrudServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
	
	@Autowired
	UserRegistrationDetailsRepo  userRegistrationDetailsRepo;

	@Override
	public List<UserRegistrationDetailsVO> findAllRegisteredUsers() {
		List<UserRegistrationDetails> usersList = userRegistrationDetailsRepo.findAll();
		List<UserRegistrationDetailsVO> usersListVO = usersList.stream().map(user -> {
			UserRegistrationDetailsVO userDetailsVO = modelMapper.map(user, UserRegistrationDetailsVO.class);
			return userDetailsVO;
		}).collect(Collectors.toList());
		return usersListVO;

	}

	
	 @Override
	 public void deleteUserByEmail(String email) {
	        Optional<UserRegistrationDetails> userOpt = userRegistrationDetailsRepo.findByEmail(email);
	        if (userOpt != null) {
	        	UserRegistrationDetails userDetails=userOpt.get();
	            userRegistrationDetailsRepo.delete(userDetails);
	        } else {
	            throw new RuntimeException("User not found with email: " + email);
	        }
	    }
	
	
	
	
}
