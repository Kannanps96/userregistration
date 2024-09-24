package com.user.userregistration.registrationandauthcontroller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.user.userregistration.config.JwtHelper;
import com.user.userregistration.conflogin.service.CustomLoginService;
import com.user.userregistration.registrationandauthcontroller.dto.LoginRequest;
import com.user.userregistration.registrationandauthcontroller.dto.LoginResponse;
import com.user.userregistration.registrationandauthcontroller.dto.UserRegistrationDetailsVO;
import com.user.userregistration.registrationandauthservice.RegistrationAndAuthService;


@RestController
@RequestMapping
public class RegistrationAndAuthController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtHelper jwtHelper;
	
	@Autowired
	CustomLoginService userLoginService;
	
	@Autowired
	RegistrationAndAuthService registrationAndAuthService;
	
    private static final Logger logger = LoggerFactory.getLogger(RegistrationAndAuthController.class);
	
	
	@PostMapping(path = "/user/registration")
	public ResponseEntity<?> userRegistration(UserRegistrationDetailsVO userDetailVO) {
		String result = "N";
		try {
			result=registrationAndAuthService.saveUserDetails(userDetailVO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
           
		}
		if (result != null && result.equals("Y")) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	   
	}
	
	
	
	
	 @PostMapping(path = "/user/apilogin")
	    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
	        logger.info("Login attempt for user: {}", request.getUsername());
	        UserDetails userDetails = null;
	        try {
	            userDetails = userLoginService.loadUserByUsername(request.getUsername());
	        } catch (UsernameNotFoundException e) {
	            logger.error("User not found: {}", request.getUsername());
	            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "USER NOT FOUND");
	        }

	        if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
	            logger.info("Authentication successful for user: {}", request.getUsername());
	            Map<String, String> claims = new HashMap<>();
	            claims.put("id", String.valueOf(userLoginService.findUserByEmail(request.getUsername()).getId()));
	            claims.put("username", request.getUsername());
		    claims.put("roles", "ADMIN");

	            String token = jwtHelper.createJwtForClaims(request.getUsername(), claims);
	            LoginResponse loginResponse = new LoginResponse(token);
	            return ResponseEntity
	    	            .status(HttpStatus.OK)
	    	            .contentType(MediaType.APPLICATION_JSON)
	    	            .body(loginResponse);
	        }

	        logger.warn("Authentication failed for user: {}", request.getUsername());
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body("USER NOT AUTHENTICATED");
	    }
}
