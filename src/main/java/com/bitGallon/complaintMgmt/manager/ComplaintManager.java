package com.bitGallon.complaintMgmt.manager;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.bitGallon.complaintMgmt.bean.MinCategorySubCategoryBean;
import com.bitGallon.complaintMgmt.bean.MinSubCategoryBean;
import com.bitGallon.complaintMgmt.entity.AttachmentDetail;
import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.EscalationHierarchy;
import com.bitGallon.complaintMgmt.entity.Role;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.repository.AttachmentDetailRepository;
import com.bitGallon.complaintMgmt.repository.ComplaintRepository;
import com.bitGallon.complaintMgmt.repository.EmployeeRepository;
import com.bitGallon.complaintMgmt.repository.EscalationHierarchyRepository;
import com.bitGallon.complaintMgmt.repository.RoleCategoryRepository;
import com.bitGallon.complaintMgmt.repository.RoleRepository;
import com.bitGallon.complaintMgmt.repository.StatusRepository;
import com.bitGallon.complaintMgmt.repository.SubCategoryRepository;
import com.bitGallon.complaintMgmt.repository.UtilRepository;
import com.bitGallon.complaintMgmt.schedular.SchedularTask;
import com.bitGallon.complaintMgmt.util.CommonUtil;

@Repository
@Transactional
public class ComplaintManager {
	
	public static final String DELIM = "-";

	@Autowired
	private ComplaintRepository repository;
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private AttachmentDetailRepository attachmentDetailRepository;
	
	@Autowired
	private EscalationHierarchyRepository escalationHierarchyRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	 @Autowired
	 private RoleCategoryRepository roleCategoryRepository;
	

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
	
	public void saveOrUpdateComplaintRegistration(ComplaintRegistration complaintRegistration) throws Exception {
		repository.saveOrUpdateComplaintRegistration(complaintRegistration);
	}
	
	public ComplaintRegistration getComplaintForUser(String complanintId, long userId) {
		return repository.getComplaintByComplaintNumber(complanintId, userId);
	}
	
	
	/*public ComplaintRegistration getComplaintForEmployee(String complanintId, long empId) {
		return repository.getComplaintForUser(complanintId, empId);
	}*/
	
	public List<ComplaintRegistration> getAllComplaintsForUser(Pageable page, Long userId, Date startDate, Date endDate, Long categoryId, List<Long> statusId){
		if(startDate == null && endDate !=null) return null;
		if(endDate == null) endDate = new Date();
		// add end date +1 
		Calendar cal= Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DATE, 1);
		endDate = cal.getTime();
		return repository.getAllComplaintsForUser(page, userId , startDate, endDate, categoryId, statusId);
	}
	
	public List<ComplaintRegistration> getAllComplaintsForEmployee(Pageable page, Long employeeId, Date startDate, Date endDate, Long subCategoryId, Long prevComplaintId, List<Long> statusId){
		if(startDate == null && endDate !=null) return null;
		if(endDate == null) endDate = new Date();
		// add end date +1 
		Calendar cal= Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DATE, 1);
		endDate = cal.getTime();
		return repository.getAllComplaintsForEmployee(page, employeeId, startDate, endDate, subCategoryId, prevComplaintId, statusId);
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
			registrationBean = UtilRepository.createComplaintRepoBean(registration , attachmentDetails);
		}
		return registrationBean;
	}
	
	public ComplaintRegistrationBean getComplaintByComplaintNumberForEmployee(String complaintNumber, Long employeeId) {
		ComplaintRegistration registration = repository.getComplaintByComplaintNumberForEmployee(complaintNumber, employeeId);
		List<AttachmentDetail> attachmentDetails = null;
		ComplaintRegistrationBean registrationBean = null;
		if(registration != null) {
			attachmentDetails = attachmentDetailRepository.getAttachments(registration.getId());
			registrationBean = UtilRepository.createComplaintRepoBean(registration , attachmentDetails);
		}
		return registrationBean;
	}
	
	public List<ComplaintRegistration> getAllUnAssiginedComplaint() {
		return repository.getAllUnAssiginedComplaints();
	}
	
	

	public ComplaintRegistration resolveComplaint(String complaintId, Long empId, String subStatus, String additionalComments) {
		ComplaintRegistration complaintRegistration = repository.getLatestComplaint(complaintId);
		if(complaintRegistration.getEmployee().getId()==empId) {
			complaintRegistration.setStatus(statusRepository.getStatus(ConstantProperty.STATUS_RESOLVED));
			complaintRegistration.setSubStatus(statusRepository.getSubStatus(subStatus));
			if(subStatus.equals(ConstantProperty.SUB_STATUS_RESOLVED_ISSUE_FIXED))
				complaintRegistration.setSubStatus(statusRepository.getSubStatus(ConstantProperty.SUB_STATUS_RESOLVED_ISSUE_FIXED));
			if(subStatus.equals(ConstantProperty.SUB_STATUS_RESOLVED_NOT_AN_ISSUE))
				complaintRegistration.setSubStatus(statusRepository.getSubStatus(ConstantProperty.SUB_STATUS_RESOLVED_NOT_AN_ISSUE));
			if(subStatus.equals(ConstantProperty.SUB_STATUS_RESOLVED_NOT_DEPARTMENTAL_ISSUE))
				complaintRegistration.setSubStatus(statusRepository.getSubStatus(ConstantProperty.SUB_STATUS_RESOLVED_NOT_DEPARTMENTAL_ISSUE));
			if(subStatus.equals(ConstantProperty.SUB_STATUS_ESCALED_REQUIRE_MORE_TIME))
				complaintRegistration.setSubStatus(statusRepository.getSubStatus(ConstantProperty.SUB_STATUS_ESCALED_REQUIRE_MORE_TIME));
			if(subStatus.equals(ConstantProperty.SUB_STATUS_RESOLVED_OTHERS)) {
				complaintRegistration.setSubStatus(statusRepository.getSubStatus(ConstantProperty.SUB_STATUS_ESCALED_OTHERS));
				complaintRegistration.setAdditionalComments(additionalComments);
			}
		}
		repository.resolveComplaint(complaintRegistration);
		return complaintRegistration;
	}
	
	public ComplaintRegistration updateComplaint(String complaintId, Long empId, String subStatus, String additionalComments) throws Exception {
		ComplaintRegistration newComplaintRegistration = null;
		ComplaintRegistration complaintRegistration = repository.getLatestComplaint(complaintId);
		if(complaintRegistration == null) return null;
		if(complaintRegistration.getEmployee() != null && complaintRegistration.getEmployee().getId()==empId) {
			newComplaintRegistration = getUpdatedComplaint(complaintRegistration);
			complaintRegistration.setStatus(statusRepository.getStatus(ConstantProperty.STATUS_CLOSED));
			complaintRegistration.setSubStatus(statusRepository.getSubStatus(ConstantProperty.SUB_STATUS_ESCALED_ESCALATED_BY_EMPLOYEE));
			complaintRegistration.setAdditionalComments(additionalComments);

			newComplaintRegistration.setStatus(statusRepository.getStatus(ConstantProperty.STATUS_ESCALATED));
			if(subStatus.equalsIgnoreCase(ConstantProperty.SUB_STATUS_ESCALED_NEED_APPROVAL))
				newComplaintRegistration.setSubStatus(statusRepository.getSubStatus(subStatus));
			if(subStatus.equalsIgnoreCase(ConstantProperty.SUB_STATUS_ESCALED_REQUIRE_MORE_TIME))
				newComplaintRegistration.setSubStatus(statusRepository.getSubStatus(subStatus));
			if(subStatus.equalsIgnoreCase(ConstantProperty.SUB_STATUS_ESCALED_STOCK_UNAVAILABLE))
				newComplaintRegistration.setSubStatus(statusRepository.getSubStatus(subStatus));
			if(subStatus.equalsIgnoreCase(ConstantProperty.SUB_STATUS_ESCALED_OTHERS)) {
				newComplaintRegistration.setSubStatus(statusRepository.getSubStatus(subStatus));
			}
			newComplaintRegistration.setAdditionalComments(CommonUtil.addPreviousComplaintStatus(newComplaintRegistration.getAdditionalComments(), complaintRegistration.getStatus(), complaintRegistration.getSubStatus()));
			repository.saveOrUpdateComplaintRegistration(complaintRegistration);
			repository.saveComplaintRegistration(newComplaintRegistration);
			return newComplaintRegistration;
		}
		return null;
	}
	
	private String getComplaintNumber() {
		return "C"+new Random().nextInt(ConstantProperty.MAX_RANDOM_NUM);
	}
	
	private ComplaintRegistration getUpdatedComplaint(ComplaintRegistration updatedComplaintRegistration) {
		ComplaintRegistration newComplaintRegistration = new ComplaintRegistration();
		newComplaintRegistration.setIsActive((short)1);
		newComplaintRegistration.setArea(updatedComplaintRegistration.getArea());
		String comp = updatedComplaintRegistration.getReferenceComplaint();
		newComplaintRegistration.setComplaintId(comp+DELIM+(updatedComplaintRegistration.getComplaintLevel()+1));
		newComplaintRegistration.setComplaintLat(updatedComplaintRegistration.getComplaintLat());
		newComplaintRegistration.setComplaintLevel((short) (updatedComplaintRegistration.getComplaintLevel()+1));
		newComplaintRegistration.setComplaintLng(updatedComplaintRegistration.getComplaintLng());
		newComplaintRegistration.setCreatedDate(updatedComplaintRegistration.getCreatedDate());
		EscalationHierarchy escalationHierarchy =null;
		if(null != updatedComplaintRegistration.getEmployee()) {
			newComplaintRegistration.setEmployee(updatedComplaintRegistration.getEmployee().getReportingEmployee());
			escalationHierarchy = escalationHierarchyRepository.getEscalationHierarchyDetail(updatedComplaintRegistration.getIssueType(), 
					newComplaintRegistration.getComplaintLevel());
		}
		if(escalationHierarchy != null)	newComplaintRegistration.setEscalatedTime(CommonUtil.getEscaltedTime(escalationHierarchy.getEscalationTime()));
		else newComplaintRegistration.setEscalatedTime(null); //no further escalation hierarchy hence complaint has reached to the highest level
		newComplaintRegistration.setIssueTitle(updatedComplaintRegistration.getIssueTitle());
		newComplaintRegistration.setIssueType(updatedComplaintRegistration.getIssueType());
		newComplaintRegistration.setLandMark(updatedComplaintRegistration.getLandMark());
		newComplaintRegistration.setReferenceComplaint(updatedComplaintRegistration.getReferenceComplaint());
		newComplaintRegistration.setRemark(updatedComplaintRegistration.getRemark());
		newComplaintRegistration.setUser(updatedComplaintRegistration.getUser());
		return newComplaintRegistration;
	}

	public void escalateComplaints(ComplaintRegistration complaint) {
		ComplaintRegistration newComplaintRegistration = null;
		newComplaintRegistration = getUpdatedComplaint(complaint);
		newComplaintRegistration.setCreatedDate(complaint.getCreatedDate());
		newComplaintRegistration.setStatus(statusRepository.getStatus(ConstantProperty.STATUS_ESCALATED));
		newComplaintRegistration.setSubStatus(statusRepository.getSubStatus(ConstantProperty.SUB_STATUS_ESCALED_ESCALATED_BY_SYSTEM));
		try {
			//create new complaint
			if (newComplaintRegistration.getEscalatedTime() != null) {
				repository.saveComplaintRegistration(newComplaintRegistration);
				// update the old complaint
				complaint.setStatus(statusRepository.getStatus(ConstantProperty.STATUS_CLOSED));
				complaint.setSubStatus(statusRepository.getSubStatus(ConstantProperty.SUB_STATUS_ESCALED_ESCALATED_BY_SYSTEM));
				repository.saveOrUpdateComplaintRegistration(complaint);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<MinCategorySubCategoryBean> getAssignedCategoriesSubCategory(Long userId) {
        MinCategorySubCategoryBean categorySubCategoryBean = new MinCategorySubCategoryBean();
        Employee emp = empRepository.getEmployee(userId.longValue());
        List<Category> categoryList = roleCategoryRepository.getAssignedCategory(emp.getRole());
        ArrayList<MinCategorySubCategoryBean> listMinCategorySubCategoryBean = null;
        if (categoryList != null) {
            listMinCategorySubCategoryBean = new ArrayList<MinCategorySubCategoryBean>();
            for (Category category : categoryList) {
                MinCategorySubCategoryBean bean = new MinCategorySubCategoryBean();
                bean.setCategoryId(category.getId());
                bean.setCategoryName(category.getName());
                bean.setSubCategories(this.subCategoryRepository.getAllSubCategories(category.getId()).stream().map(subCategory -> {
                    MinSubCategoryBean minSubCategoryBean = new MinSubCategoryBean();
                    minSubCategoryBean.setId(subCategory.getId());
                    minSubCategoryBean.setName(subCategory.getName());
                    return minSubCategoryBean;
                }).collect(Collectors.toList()));
                listMinCategorySubCategoryBean.add(bean);
            }
        }
        return listMinCategorySubCategoryBean;
    }

    public List<Category> getAssignedCategories(Long userId) {
        Employee emp = empRepository.getEmployee(userId.longValue());
        List categoryList = roleCategoryRepository.getAssignedCategory(emp.getRole());
        return categoryList;
    }
}
