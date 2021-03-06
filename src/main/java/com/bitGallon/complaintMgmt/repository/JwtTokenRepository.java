package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.JwtToken;
import com.bitGallon.complaintMgmt.entity.User;

/**
 * @author rpsingh
 *
 */

@Repository
@Transactional
public class JwtTokenRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Long createAccessToken(User user, String accessToken, String accessKey) 
	  throws Exception 
	{
		JwtToken jwt = getJwtTokenByUser(user);
		if(jwt == null) {
			jwt = new JwtToken();
			jwt.setAccessKey(accessKey);
			jwt.setAccessToken(accessToken);
			jwt.setUser(user);
			Long id = (Long) getSession().save(jwt);
			return id;
		} 
		jwt.setAccessKey(accessKey);
		jwt.setAccessToken(accessToken);
		getSession().saveOrUpdate(jwt);
		return jwt.getId();
	}
	
	public Long createAccessTokenEmployee(Employee employee, String accessToken, String accessKey)
	  throws Exception 
	{
		JwtToken jwt = getJwtTokenByEmployee(employee);
		if(jwt == null) {
			jwt = new JwtToken();
			jwt.setAccessKey(accessKey);
			jwt.setAccessToken(accessToken);
			jwt.setEmployee(employee);
			Long id = (Long) getSession().save(jwt);
			return id;
		} 
		jwt.setAccessKey(accessKey);
		jwt.setAccessToken(accessToken);
		getSession().saveOrUpdate(jwt);
		return jwt.getId();
	}

	@Deprecated
	public int updateAccessToken(String accessKey, String accessToken,
								  String refreshToken) throws Exception 
	{
		Query query = getSession().createQuery("UPDATE JwtToken SET accessKey = :p1 AND accessToken =: p2" 
											  +" WHERE refreshToken = :p3");
		query.setParameter("p1", accessKey);
		query.setParameter("p2", accessToken);
		query.setParameter("p3", refreshToken);
		int result = query.executeUpdate();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JwtToken getJwtTokenByAccessToken(String accessToken) {
		List<JwtToken> jwtTokenList = getSession()
								   	 .createQuery("FROM JwtToken jt WHERE jt.accessToken=:p1")
								     .setParameter("p1", accessToken).list();

		if(jwtTokenList.size() != 0) {
			return jwtTokenList.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public JwtToken getJwtTokenByUser(User user) {
		List<JwtToken> jwtTokenList = getSession()
									 .createQuery("FROM JwtToken jt WHERE jt.user.id=:p1")
									 .setParameter("p1", user.getId()).list();

		if(jwtTokenList.size() != 0) {
			return jwtTokenList.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public JwtToken getJwtTokenByEmployee(Employee employee) {
		List<JwtToken> jwtTokenList = getSession()
									 .createQuery("FROM JwtToken jt WHERE jt.employee.id=:p1")
									 .setParameter("p1", employee.getId()).list();

		if(jwtTokenList.size() != 0) {
			return jwtTokenList.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Deprecated	
	public boolean isTokenExsit(String accessToken, String refreshToken) {
		List<JwtToken> jwtTokenList = getSession()
									 .createQuery("FROM JwtToken jt WHERE jt.accessToken=:p1 AND jt.refreshToken =:p2")
									 .setParameter("p1", accessToken).setParameter("p2", refreshToken).list();
		if(jwtTokenList.size() != 0) {
			return true;
		}
		return false;
	}
}
