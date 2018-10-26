package com.bitGallon.complaintMgmt.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private  JwtTokenRepository jwtTokenDAO;
	
	public Long createAccessToken(User user, String accessToken, String accessKey) throws Exception {
		return jwtTokenDAO.createAccessToken(user, accessToken, accessKey);
	}
	
	public JwtToken getJwtTokenByAccessToken(String accessToken) {
		return jwtTokenDAO.getJwtTokenByAccessToken(accessToken);
	}
}
