package com.bitGallon.complaintMgmt.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.ComplaintRegistrationBean;
import com.bitGallon.complaintMgmt.entity.AttachmentDetail;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.EscalationHierarchy;
import com.bitGallon.complaintMgmt.entity.Role;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.repository.AttachmentDetailRepository;
import com.bitGallon.complaintMgmt.repository.ComplaintRepository;
import com.bitGallon.complaintMgmt.repository.EmployeeRepository;
import com.bitGallon.complaintMgmt.repository.EscalationHierarchyRepository;
import com.bitGallon.complaintMgmt.repository.RoleRepository;
import com.bitGallon.complaintMgmt.schedular.SchedularTask;
import com.bitGallon.complaintMgmt.util.CommonUtil;

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
	
	@Autowired
	private AttachmentDetailRepository attachmentDetailRepository;
	
	@Autowired
	private EscalationHierarchyRepository escalationHierarchyRepository;
	
	

	public ComplaintRegistration saveComplaintRegistration(ComplaintRegistration complaintRegistration) throws Exception {
		List<Employee> empList = empRepository.getEmployee(complaintRegistration.getIssueType().getRole(), complaintRegistration.getArea());
		if(empList != null) {
			HashMap<Employee, Integer> empCountHM = complaintRepository.getAssignedEmployee(empList);
			Employee assignedEmployee = CommonUtil.findAssignedEmployee(empCountHM);
			complaintRegistration.setEmployee(assignedEmployee);
			EscalationHierarchy escalationHierarchy = escalationHierarchyRepository.getEscalationHierarchyDetail(complaintRegistration.getIssueType(), (short)0);
			complaintRegistration.setEscalatedTime(CommonUtil.getEscaltedTime(escalationHierarchy.getEscalationTime()));
		} else {
			complaintRegistration.setEmployee(null);
			complaintRegistration.setEscalatedTime(null);
			SchedularTask.setAssignEmployeeTask(true);
		}
		String comp = getComplaintNumber();
		complaintRegistration.setComplaintId(comp+DELIM+complaintRegistration.getComplaintLevel());
		complaintRegistration.setReferenceComplaint(comp);
		return repository.saveComplaintRegistration(complaintRegistration);
	}
	
	public ComplaintRegistration getComplaintForUser(String complanintId, long userId) {
		return repository.getComplaintByComplaintNumber(complanintId, userId);
	}
	
	
	/*public ComplaintRegistration getComplaintForEmployee(String complanintId, long empId) {
		return repository.getComplaintForUser(complanintId, empId);
	}*/
	
	public List<ComplaintRegistration> getAllComplaintsForUser(Pageable page, Long userId, Date startDate, Date endDate, Long categoryId){
		if(startDate == null && endDate !=null) return null;
		if(endDate == null) endDate = new Date();
		
		return repository.getAllComplaintsForUser(page, userId , startDate, endDate, categoryId);
	}
	
	public List<ComplaintRegistration> getAllComplaintsForEmployee(Pageable page, Long employeeId, Date startDate, Date endDate, Long subCategoryId){
		if(startDate == null && endDate !=null) return null;
		if(endDate == null) endDate = new Date();
		
		return repository.getAllComplaintsForEmployee(page, employeeId, startDate, endDate, subCategoryId);
	}
	/*public List<ComplaintRegistration> getAllComplaintsForEmp(Pageable page, long empId){
		return repository.getAllComplaints(page, empId);
	}*/

	public Boolean updateIsActive(long id, short isActive) {
		return repository.updateIsActive(id, isActive);
	}
	
	public ComplaintRegistrationBean getComplaintByComplaintNumber(String complaintNumber, Long userId) {
		ComplaintRegistration registration = repository.getComplaintByComplaintNumber(complaintNumber, userId);
		List<AttachmentDetail> attachmentDetails = new ArrayList<>();
		ComplaintRegistrationBean registrationBean = null;
		if(registration != null) {
			attachmentDetails = attachmentDetailRepository.getAttachments(registration.getId());
			registrationBean = createComplaintRepoBean(registration , attachmentDetails);
		}
		return registrationBean;
	}
	
	public ComplaintRegistrationBean getComplaintByComplaintNumberForEmployee(String complaintNumber, Long employeeId) {
		ComplaintRegistration registration = repository.getComplaintByComplaintNumberForEmployee(complaintNumber, employeeId);
		List<AttachmentDetail> attachmentDetails = null;
		ComplaintRegistrationBean registrationBean = null;
		if(registration != null) {
			attachmentDetails = attachmentDetailRepository.getAttachments(registration.getId());
			registrationBean = createComplaintRepoBean(registration , attachmentDetails);
		}
		return registrationBean;
	}
	
	public List<ComplaintRegistration> getAllUnAssiginedComplaint() {
		return repository.getAllUnAssiginedComplaints();
	}
	
	private ComplaintRegistrationBean createComplaintRepoBean(ComplaintRegistration registration, List<AttachmentDetail> attachmentDetails) {
		ComplaintRegistrationBean bean = new ComplaintRegistrationBean();
		bean.setId(registration.getId());
		bean.setReferenceComplaint(registration.getReferenceComplaint());
		bean.setAdditionalComments(registration.getAdditionalComments());
		bean.setAreaName(registration.getArea().getName());
		bean.setComplaintBy(registration.getUser().getMobileNumber());
		bean.setComplaintLat(registration.getComplaintLat());
		bean.setComplaintLng(registration.getComplaintLng());
		bean.setEmployeeMobileNumber(registration.getEmployee().getName());
		bean.setEmployeeName(registration.getEmployee().getRegisteredMobileNo());
		bean.setIssueName(registration.getIssueType().getName());
		bean.setIssueTitle(registration.getIssueTitle());
		bean.setStatus(registration.getStatus().getStatus());
		bean.setSubStatus(registration.getSubStatus().getStatus());
		bean.setDesignation(registration.getEmployee().getRole().getRoleName());
		List<String> attachmentBeans = null;
		if(!attachmentDetails.isEmpty()) {
			attachmentBeans = attachmentDetails.stream().map(attachmentDetail -> attachmentDetail.getName()).collect(Collectors.toList());
		}
		bean.setAttachmentsFiles(attachmentBeans);
		return bean;
	}

	private String getComplaintNumber() {
		return "C"+new Random().nextInt(ConstantProperty.MAX_RANDOM_NUM);
	}
	
}
