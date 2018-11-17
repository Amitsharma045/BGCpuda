package com.bitGallon.complaintMgmt.manager;


import org.springframework.beans.factory.annotation.Autowired;
import com.bitGallon.complaintMgmt.entity.Area;
import com.bitGallon.complaintMgmt.repository.AreaRepository;

public class AreaManager  {
	
	@Autowired
	private AreaRepository repository;
	
	public Area getArea(String aspectName) {
		return repository.getArea(aspectName);
	}
}
