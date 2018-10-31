package com.bitGallon.complaintMgmt.manager;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitGallon.complaintMgmt.entity.AspectRating;
import com.bitGallon.complaintMgmt.repository.AspectRatingRepository;

/**
 * @author rpsingh
 *
 */
@Service
@Transactional
public class AspectRatingManager {

	@Autowired
	private AspectRatingRepository repository;
	
	public void saveAspectRating(List<AspectRating> listAspectRating) {
		repository.saveAspectRating(listAspectRating);
	}
}
