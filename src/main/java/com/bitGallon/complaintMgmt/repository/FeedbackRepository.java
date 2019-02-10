package com.bitGallon.complaintMgmt.repository;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.Feedback;

/**
 * @author rpsingh
 *
 */

@Repository
@Transactional
public class FeedbackRepository {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Long saveFeedback(Feedback feedback) {
		getSession().saveOrUpdate(feedback);
		return feedback.getId();
	}

}
