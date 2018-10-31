package com.bitGallon.complaintMgmt.manager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitGallon.complaintMgmt.entity.Aspect;
import com.bitGallon.complaintMgmt.repository.AspectRepository;


/**
 * @author rpsingh
 *
 */
@Service
@Transactional
public class AspectManager {
	
	@Autowired
	private AspectRepository repository;
	
	public Aspect getAspect(String aspectName) {
		return repository.getAspect(aspectName);
	}

}
