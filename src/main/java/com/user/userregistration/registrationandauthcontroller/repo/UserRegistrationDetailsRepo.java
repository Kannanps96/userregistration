package com.user.userregistration.registrationandauthcontroller.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.userregistration.registrationandauthcontroller.entities.UserRegistrationDetails;


public interface UserRegistrationDetailsRepo extends JpaRepository<UserRegistrationDetails, Long> {

	Optional<UserRegistrationDetails> findByEmail(String username);

	
}
