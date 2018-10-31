package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.Aspect;


/**
 * @author rpsingh
 *
 */

@Repository
@Transactional
public class AspectRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public Aspect getAspect(String aspectName) {
		List<Aspect> aspectlist = getSession()
				.createQuery("FROM Aspect ap WHERE ap.name =:p1")
				.setParameter("p1", aspectName).list();
		if(aspectlist.size() != 0) {
			return aspectlist.get(0);
		}
		return null;
	}
}
