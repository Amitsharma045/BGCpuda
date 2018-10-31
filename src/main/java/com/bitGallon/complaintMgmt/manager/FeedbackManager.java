package com.bitGallon.complaintMgmt.manager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitGallon.complaintMgmt.entity.Feedback;
import com.bitGallon.complaintMgmt.repository.FeedbackRepository;


/**
 * @author rpsingh
 *
 */
@Service
@Transactional
public class FeedbackManager {

	@Autowired
	private FeedbackRepository repository;
	
	public Long saveFeedback(Feedback feedback) {
		return repository.saveFeedback(feedback);
	}
}
