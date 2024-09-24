package com.user.userregistration.crudcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.userregistration.crudservice.CrudService;
import com.user.userregistration.registrationandauthcontroller.dto.UserRegistrationDetailsVO;


@RestController
@RequestMapping("/adminuser")
public class CrudController {
	
	
	@Autowired
	CrudService crudService;
	
	
	@GetMapping(path = "/getAllUsers")
	@ResponseBody
	public ResponseEntity<List<UserRegistrationDetailsVO>> userRegistration() {
		List<UserRegistrationDetailsVO> usersList = crudService
				.findAllRegisteredUsers();
		return new ResponseEntity<>(usersList, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/userOperations/delete/{email}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteUser(@PathVariable String email) {
		try {
			crudService.deleteUserByEmail(email);
			return ResponseEntity.ok("User deleted successfully.");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	
}
