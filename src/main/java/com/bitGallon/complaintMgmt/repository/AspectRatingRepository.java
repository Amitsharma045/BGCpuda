package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.AspectRating;

/**
 * @author rpsingh
 *
 */

@Repository
@Transactional
public class AspectRatingRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void saveAspectRating(List<AspectRating> listAspectRating) {
		for(AspectRating rating : listAspectRating) {
			getSession().save(rating);
		}
	}
}
