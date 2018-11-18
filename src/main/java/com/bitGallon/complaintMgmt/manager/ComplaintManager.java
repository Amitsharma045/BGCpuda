package com.bitGallon.complaintMgmt.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.CategoryBean;
import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.Role;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.repository.CategoryRepository;
import com.bitGallon.complaintMgmt.repository.ComplaintRepository;
import com.bitGallon.complaintMgmt.repository.EmployeeRepository;
import com.bitGallon.complaintMgmt.repository.IssueTypeRepository;
import com.bitGallon.complaintMgmt.repository.RoleRepository;

@Repository
@Transactional
public class ComplaintManager {
	
	private static final String DELIM = "-";

	@Autowired
	private ComplaintRepository repository;
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	

	public ComplaintRegistration saveComplaintRegistration(ComplaintRegistration complaintRegistration) throws Exception {
		Role role = roleRepository.getRole(complaintRegistration.getArea(), complaintRegistration.getIssueType().getSubCategory().getCategory());
		List<Employee> empList = empRepository.getEmployee(role);
		HashMap<Employee, Integer> empCountHM = complaintRepository.getAssignedEmployee(empList);
		Employee assignedEmployee = findAssignedEmployee(empCountHM);
		complaintRegistration.setEmployee(assignedEmployee);
		String comp = getComplaintNumber();
		complaintRegistration.setComplaintId(comp+DELIM+complaintRegistration.getComplaintLevel());
		complaintRegistration.setReferenceComplaint(comp);
		return repository.saveComplaintRegistration(complaintRegistration);
	}
	
	public ComplaintRegistration getComplaintForUser(String complanintId, long userId) {
		return repository.getComplaintForUser(complanintId, userId);
	}
	
	/*public ComplaintRegistration getComplaintForEmployee(String complanintId, long empId) {
		return repository.getComplaintForUser(complanintId, empId);
	}*/
	
	public List<ComplaintRegistration> getAllComplaintsForUser(Pageable page, Long userId, Date startDate, Date endDate, Long categoryId){
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
	
	public ComplaintRegistration getComplaintByComplaintNumber(String complaintNumber) {
		return repository.getComplaintByComplaintNumber(complaintNumber);
	}
	
	private Employee findAssignedEmployee(HashMap<Employee, Integer> empHM) {
		int temp = (int) empHM.values().toArray()[0];
		Employee emp = empHM.keySet().stream().findFirst().get();
		for (Entry<Employee, Integer> entry : empHM.entrySet()) {
			if(entry.getValue() < temp) {
				emp = entry.getKey();
				temp = entry.getValue();
			} 
		}
		return emp;
	}
	
	private String getComplaintNumber() {
		return "C"+new Random().nextInt(ConstantProperty.MAX_RANDOM_NUM);
	}
	
}
