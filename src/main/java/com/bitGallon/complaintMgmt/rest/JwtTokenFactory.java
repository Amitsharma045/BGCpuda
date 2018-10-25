package com.bitGallon.complaintMgmt.rest;

import java.util.Date;

import com.bitGallon.complaintMgmt.util.CommonUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author rpsingh
 *
 */
public class JwtTokenFactory {
	
	public static String createAccessJwtToken(String id, String secretKey) 
		    throws Exception 
		  {
		    // TODO: Summer18 - Need to include a property to define the token expire period
		    Date expiration = CommonUtil.getExpiryDate();
		    
		    return Jwts.builder().
		        setSubject(id).
//		        setExpiration(expiration).
		        signWith(SignatureAlgorithm.HS512, secretKey).compact();
		  }
	
	  public static String createRefreshJwtToken(String id, String role, String secretKey) throws Exception {
		    return Jwts.builder().setSubject(id).claim("role", role).
		        signWith(SignatureAlgorithm.HS512, secretKey).compact();
		  }
}
