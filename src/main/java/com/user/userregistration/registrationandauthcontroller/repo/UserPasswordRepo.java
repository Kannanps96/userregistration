package com.user.userregistration.registrationandauthcontroller.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.userregistration.registrationandauthcontroller.entities.UserPassword;


public interface UserPasswordRepo extends JpaRepository<UserPassword, Long> {

	Optional<UserPassword> findByUserDetailsId(Long id);


	
}
