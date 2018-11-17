package com.bitGallon.complaintMgmt.manager;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitGallon.complaintMgmt.entity.Area;
import com.bitGallon.complaintMgmt.repository.AreaRepository;

@Service
@Transactional
public class AreaManager  {
	
	@Autowired
	private AreaRepository repository;
	
	public Area getArea(String aspectName) {
		return repository.getArea(aspectName);
	}
}
