package com.user.userregistration.conflogin.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.userregistration.conflogin.service.CustomLoginService;
import com.user.userregistration.registrationandauthcontroller.dto.UserRegistrationDetailsVO;
import com.user.userregistration.registrationandauthcontroller.entities.UserPassword;
import com.user.userregistration.registrationandauthcontroller.entities.UserRegistrationDetails;
import com.user.userregistration.registrationandauthcontroller.repo.UserPasswordRepo;
import com.user.userregistration.registrationandauthcontroller.repo.UserRegistrationDetailsRepo;

@Service
public class CustomLoginServiceImpl implements CustomLoginService {
	
	 private final ModelMapper modelMapper;
		
		@Autowired
	    public CustomLoginServiceImpl(ModelMapper modelMapper) {
	        this.modelMapper = modelMapper;
	    }
		
		
	
      @Autowired
      UserRegistrationDetailsRepo  userDetailsRepository;
      
      @Autowired
      UserPasswordRepo  userPasswordRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserRegistrationDetails> optionalUser = userDetailsRepository.findByEmail(username);
		if(optionalUser.isPresent()) {
			UserRegistrationDetails userSeat = optionalUser.get();
			Optional<UserPassword> optionalUserPass = userPasswordRepo.findByUserDetailsId(userSeat.getId());
			GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
			Collection<GrantedAuthority> authorities = Collections.singleton(authority);
			return new User(username, optionalUserPass.get().getPassword(), authorities);
		}
		
		throw new UsernameNotFoundException("INVALID USER CREDENTIALS");
	}

	@Override
	public UserRegistrationDetailsVO findUserByEmail(String username) {
		Optional<UserRegistrationDetails> optionalUser = userDetailsRepository.findByEmail(username);
		UserRegistrationDetailsVO userSeat=new UserRegistrationDetailsVO();
		if(optionalUser.isPresent()) {
			 userSeat = modelMapper.map(optionalUser.get(),UserRegistrationDetailsVO.class);
		}
		return userSeat;
	}

	
	

}
