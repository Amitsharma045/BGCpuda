package com.bitGallon.complaintMgmt.manager;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bitGallon.complaintMgmt.entity.ComplaintStatus;
import com.bitGallon.complaintMgmt.repository.StatusRepository;

@Repository
@Transactional
public class StatusManager {
	@Autowired
	private StatusRepository repository;

	public Long saveStatus(ComplaintStatus status) throws Exception {
		return repository.saveStatus(status);
	}
	
	public ComplaintStatus getStatus(int id) {
		return repository.getStatus(id);
	}
	
	public List<ComplaintStatus> getAllStatus(){
		return repository.getAllStatuses();
	}
}
