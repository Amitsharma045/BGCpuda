package com.bitGallon.complaintMgmt.manager;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.ComplaintStatusBean;
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
	
	public ComplaintStatusBean getStatus(long id) {
		return repository.getStatus(id);
	}
	
	public List<ComplaintStatusBean> getAllStatus(Long parentId){
		if(parentId !=null) return repository.getAllStatuses(parentId);
		return repository.getAllStatuses();
	}
}
