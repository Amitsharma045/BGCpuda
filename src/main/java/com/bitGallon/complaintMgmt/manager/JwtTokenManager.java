package com.bitGallon.complaintMgmt.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.JwtToken;
import com.bitGallon.complaintMgmt.entity.User;
import com.bitGallon.complaintMgmt.repository.JwtTokenRepository;


/**
 * @author rpsingh
 *
 */
@Service
public class JwtTokenManager {

	@Autowired
	private  JwtTokenRepository repository;
	
	public Long createAccessToken(User user, String accessToken, String accessKey) throws Exception {
		return repository.createAccessToken(user, accessToken, accessKey);
	}
	
	public Long createAccessTokenEmployee(Employee employee, String accessToken, String accessKey) throws Exception {
		return repository.createAccessTokenEmployee(employee, accessToken, accessKey);
	}
	
	public JwtToken getJwtTokenByAccessToken(String accessToken) {
		return repository.getJwtTokenByAccessToken(accessToken);
	}
}
