package com.bitGallon.complaintMgmt.manager;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.CategoryBean;
import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.repository.CategoryRepository;
import com.bitGallon.complaintMgmt.repository.ComplaintRepository;

@Repository
@Transactional
public class ComplaintManager {
	@Autowired
	private ComplaintRepository repository;

	public Long saveComplaintRegistration(ComplaintRegistration complaintRegistration) throws Exception {
		return repository.saveComplaintRegistration(complaintRegistration);
	}
	
	public ComplaintRegistration getComplaintForUser(String complanintId, long userId) {
		return repository.getComplaintForUser(complanintId, userId);
	}
	
	/*public ComplaintRegistration getComplaintForEmployee(String complanintId, long empId) {
		return repository.getComplaintForUser(complanintId, empId);
	}*/
	
	public List<ComplaintRegistration> getAllComplaintsForUser(Pageable page, String userId, Date startDate, Date endDate, Long categoryId){
		if(startDate == null && endDate !=null) return null;
		if(endDate == null) endDate = new Date();
		return repository.getAllComplaintsForUser(page, userId , startDate, endDate, categoryId);
	}
	
	/*public List<ComplaintRegistration> getAllComplaintsForEmp(Pageable page, long empId){
		return repository.getAllComplaints(page, empId);
	}*/

	public Boolean updateIsActive(long id, short isActive) {
		return repository.updateIsActive(id, isActive);
	}
}
