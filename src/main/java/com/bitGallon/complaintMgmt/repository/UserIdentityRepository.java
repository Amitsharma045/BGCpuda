package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.User;

/**
 * @author rpsingh
 *
 */

@Repository
@Transactional
public class UserIdentityRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public User getUserById(String phoneNumber) throws Exception {
		List<User> userlist = getSession()
							 .createQuery("FROM User u WHERE u.userId =:p1")
							 .setParameter("p1", phoneNumber).list();
		if(userlist.size() != 0) {
			return userlist.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByMobileNumber(String mobileNumber) throws Exception {
		List<User> userlist = getSession()
							 .createQuery("FROM User u WHERE u.mobileNumber =:p1")
							 .setParameter("p1", mobileNumber).list();
		if(userlist.size() != 0) {
			return userlist.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByEmail(String emailId) throws Exception {
		List<User> userlist = getSession()
							 .createQuery("FROM User u WHERE u.emailId =:p1")
							 .setParameter("p1", emailId).list();
		if(userlist.size() != 0) {
			return userlist.get(0);
		}
		return null;
	}
	
	public Long saveUser(User user) throws Exception {
		Long id = (Long) getSession().save(user);
		return id;
	}
	
	public void saveUpdateUser(User user) throws Exception {
		getSession().saveOrUpdate(user);
	}
	
	public User getUserById(Long id) throws Exception {
		return getSession().get(User.class,id);
	}
	
	public String getUserMobileNo(Long id) {
		Criteria criteria = getSession().createCriteria(User.class);
		return (String) criteria.setProjection(Projections.property("mobileNumber")).add(Restrictions.eq("id", id)).list().get(0);
	}
} 