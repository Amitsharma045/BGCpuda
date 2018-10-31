package com.bitGallon.complaintMgmt.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitGallon.complaintMgmt.entity.User;
import com.bitGallon.complaintMgmt.repository.UserIdentityRepository;


/**
 * @author rpsingh
 *
 */
@Service
public class AuthenticationManager {
	
	@Autowired
	private  UserIdentityRepository repository;
	
	public User getUserById(String phoneNumber) throws Exception {
		return repository.getUserById(phoneNumber);
	}
	
	public User getUserByMobileNumber(String phoneNumber) throws Exception {
		return repository.getUserByMobileNumber(phoneNumber);
	}

	public User getUserByEmailId(String emailId) throws Exception {
		return repository.getUserByEmail(emailId);
	}
	
	public Long saveUser(User user) throws Exception {
		return repository.saveUser(user);
	}
	
	public void saveUpdateUser(User user) throws Exception {
		repository.saveUpdateUser(user);
	}

}
