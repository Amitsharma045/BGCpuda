package com.bitGallon.complaintMgmt.repository;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

import com.bitGallon.complaintMgmt.bean.CategoryBean;
import com.bitGallon.complaintMgmt.bean.ComplaintMinBean;
import com.bitGallon.complaintMgmt.bean.ComplaintRegistrationBean;
import com.bitGallon.complaintMgmt.bean.ComplaintStatusBean;
import com.bitGallon.complaintMgmt.bean.IssueTypeBean;
import com.bitGallon.complaintMgmt.bean.ParentComplaintStatusBean;
import com.bitGallon.complaintMgmt.bean.RemarkBean;
import com.bitGallon.complaintMgmt.bean.SubCategoryBean;
import com.bitGallon.complaintMgmt.entity.AttachmentDetail;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;


public class UtilRepository {
	public static final String ISSUE_TYPE_ALIAS = "ISSUETYPE";
	public static final String SUB_CATEGORY_ALIAS = "SUBCATEGORY";
	public static final String CATEGORY_ALIAS = "CATEGORY";
	public static final String REMARK_ALIAS = "REMARK";
	public static final String STATUS_ALIAS = "STATUS";
	public static final String PARENT_STATUS_ALIAS = "PARENT_STATUS";
	public static final String COMPLAINT_REG = "COMPLAINT_REG";
	public static final String COMPLAINT_REG_MIN = "COMPLAINT_REG_MIN";
	public static final String EMPLOYEE_ALIAS = "EMPLOYEE_ALIAS";
	public static final String USER_ALIAS = "USER_ALIAS";
	public static final String REF_COMPLAINT = "REF_COMPLAINT";
	private static final String AREA_ALIAS = "AREA_ALIAS";

	public static String getIsActiveQuery(String aliasName) {
		return " " + aliasName + ".isActive = 1";
	}

	public static Criterion isActiveRestricition() {
		return Restrictions.eq("isActive", (short) 1);
	}

	public static Criteria transferToIssueTypeBean(Criteria criteria) {
		ProjectionList projList = Projections.projectionList(); 
		return criteria.createAlias(ISSUE_TYPE_ALIAS + ".subCategory", SUB_CATEGORY_ALIAS)
				.createAlias(SUB_CATEGORY_ALIAS + ".category", CATEGORY_ALIAS)
				.setProjection(projList.add(Projections.property(ISSUE_TYPE_ALIAS + ".id"), "id")
						.add(Projections.property(ISSUE_TYPE_ALIAS + ".name"), "name")
						.add(Projections.property(SUB_CATEGORY_ALIAS + ".id"), "subCategoryId")
						.add(Projections.property(SUB_CATEGORY_ALIAS + ".name"), "subCategoryName")
						.add(Projections.property(CATEGORY_ALIAS + ".id"), "categoryId")
						.add(Projections.property(CATEGORY_ALIAS + ".name"), "categoryName"))
				.setResultTransformer(new AliasToBeanResultTransformer(IssueTypeBean.class)); 
	}

	public static Criteria setResultTransformer(Criteria criteria, Class cls) {
		return criteria.setResultTransformer(new AliasToBeanResultTransformer(cls));
	}
	public static Criteria transferToSubCategoryBean(Criteria criteria) {
		return criteria.createAlias(SUB_CATEGORY_ALIAS + ".category", CATEGORY_ALIAS)
				.setProjection(Projections.projectionList().add(Projections.property(SUB_CATEGORY_ALIAS + ".id"), "id")
						.add(Projections.property(SUB_CATEGORY_ALIAS + ".name"), "name")
						.add(Projections.property(CATEGORY_ALIAS + ".id"), "categoryId")
						.add(Projections.property(CATEGORY_ALIAS + ".name"), "categoryName"))
				.setResultTransformer(new AliasToBeanResultTransformer(SubCategoryBean.class));
	}

	public static Criteria transferToCategoryBean(Criteria criteria) {
		return criteria
				.setProjection(Projections.projectionList().add(Projections.property(CATEGORY_ALIAS + ".id"), "id")
						.add(Projections.property(CATEGORY_ALIAS + ".name"), "name"))
				.setResultTransformer(new AliasToBeanResultTransformer(CategoryBean.class));
	}

	public static Criteria transferToRemarkBean(Criteria criteria) {
		return criteria
				.setProjection(Projections.projectionList().add(Projections.property(REMARK_ALIAS + ".id"), "id")
						.add(Projections.property(REMARK_ALIAS + ".remark"), "remark"))
				.setResultTransformer(new AliasToBeanResultTransformer(RemarkBean.class));
	}

	public static Criteria transferToStatusBean(Criteria criteria) {
		return criteria.createAlias(STATUS_ALIAS + ".parentStatus", PARENT_STATUS_ALIAS)
				.setProjection(Projections.projectionList().add(Projections.property(STATUS_ALIAS + ".id"), "id")
						.add(Projections.property(STATUS_ALIAS + ".status"), "status")
						.add(Projections.property(PARENT_STATUS_ALIAS + ".id"), "parentStatusId")
						.add(Projections.property(PARENT_STATUS_ALIAS + ".status"), "parentStatus"))
				.setResultTransformer(new AliasToBeanResultTransformer(ComplaintStatusBean.class));
	}
	
	public static Criteria transferToComplaintBean(Criteria criteria) {
		criteria.createAlias(COMPLAINT_REG + ".issueType", ISSUE_TYPE_ALIAS).
		createAlias(ISSUE_TYPE_ALIAS + ".subCategory", SUB_CATEGORY_ALIAS).
		createAlias(SUB_CATEGORY_ALIAS + ".category", CATEGORY_ALIAS).
		createAlias(COMPLAINT_REG + ".employee", EMPLOYEE_ALIAS).
		createAlias(COMPLAINT_REG + ".status", PARENT_STATUS_ALIAS).
		createAlias(COMPLAINT_REG + ".subStatus", STATUS_ALIAS).
		createAlias(COMPLAINT_REG + ".area", AREA_ALIAS).
		createAlias(COMPLAINT_REG + ".remark", REMARK_ALIAS).
		createAlias(COMPLAINT_REG + ".user", USER_ALIAS);
		return criteria
				.setProjection(Projections.projectionList().add(Projections.property(COMPLAINT_REG + ".id"), "id")
						.add(Projections.property(COMPLAINT_REG + ".complaintId"), "complaintId")
						.add(Projections.property(COMPLAINT_REG + ".complaintLevel"), "complaintLevel")
						.add(Projections.property(COMPLAINT_REG + ".complaintLat"), "complaintLat")
						.add(Projections.property(COMPLAINT_REG + ".complaintLng"), "complaintLng")
						.add(Projections.property(COMPLAINT_REG + ".referenceComplaint"), "referenceComplaint")
						.add(Projections.property(COMPLAINT_REG + ".additionalComments"), "additionalComments")
						.add(Projections.property(COMPLAINT_REG + ".complaintLevel"), "complaintLevel")
						.add(Projections.property(COMPLAINT_REG + ".complaintLevel"), "complaintLevel")
						.add(Projections.property(COMPLAINT_REG + ".complaintLevel"), "complaintLevel"))
				.setResultTransformer(new AliasToBeanResultTransformer(ComplaintRegistrationBean.class));
	}
	
	public static Criteria transferToEmployeeBean(Criteria criteria) {
		return null;
	}
	
	public static Criteria transferToMiniComplaintBean(Criteria criteria) {
		criteria.createAlias(COMPLAINT_REG_MIN + ".issueType", ISSUE_TYPE_ALIAS).
				createAlias(ISSUE_TYPE_ALIAS + ".subCategory", SUB_CATEGORY_ALIAS).
				createAlias(SUB_CATEGORY_ALIAS + ".category", CATEGORY_ALIAS).
				createAlias(COMPLAINT_REG_MIN + ".employee", EMPLOYEE_ALIAS).
				createAlias(COMPLAINT_REG_MIN + ".status", PARENT_STATUS_ALIAS).
				createAlias(COMPLAINT_REG_MIN + ".subStatus", STATUS_ALIAS).
				createAlias(COMPLAINT_REG_MIN + ".user", USER_ALIAS);
		ProjectionList projList = Projections.projectionList();
		criteria.setProjection(projList.add(Projections.distinct(Projections.property(COMPLAINT_REG_MIN + ".issueTitle")), "issueTitle").
		add(Projections.property(USER_ALIAS + ".mobileNumber"), "complaintBy").
		add(Projections.property(COMPLAINT_REG_MIN + ".id"), "id").
		add(Projections.property(COMPLAINT_REG_MIN + ".referenceComplaint"), "referenceComplaint").
		add(Projections.property(ISSUE_TYPE_ALIAS + ".name"), "issueType").
		add(Projections.property(SUB_CATEGORY_ALIAS + ".name"), "subCategory").
		add(Projections.property(CATEGORY_ALIAS + ".name"), "category").
		add(Projections.property(EMPLOYEE_ALIAS + ".registeredMobileNo"), "employeeNo").
		add(Projections.property(PARENT_STATUS_ALIAS + ".status"), "status").
		add(Projections.property(STATUS_ALIAS + ".status"), "subStatus").
		add(Projections.property(EMPLOYEE_ALIAS + ".name"), "employeeName"));
		criteria.setResultTransformer(new AliasToBeanResultTransformer(ComplaintMinBean.class));
		return criteria;
	}
	
	public static List<ComplaintMinBean> transferToMiniComplaintBean(List<ComplaintRegistration> complaintRegistration) {
		List<ComplaintMinBean> complaintMinBeans = new ArrayList<>();
		if(complaintRegistration == null || complaintRegistration.size()==0) return null;
		complaintMinBeans = complaintRegistration.stream().map( complaint -> {
			ComplaintMinBean minBean = new ComplaintMinBean();
			minBean.setCategory(complaint.getIssueType().getSubCategory().getCategory().getName());
			minBean.setComplaintBy(complaint.getUser().getUserName()==null?complaint.getUser().getMobileNumber():complaint.getUser().getUserName());
			minBean.setCreatedDate(UtilRepository.getDateInFormat(complaint.getCreatedDate()));
			minBean.setEmployeeName(complaint.getEmployee()==null?"":complaint.getEmployee().getName());
			minBean.setEmployeeNo(complaint.getEmployee()==null?"":complaint.getEmployee().getRegisteredMobileNo());
			minBean.setId(complaint.getId());
			minBean.setIssueTitle(complaint.getIssueTitle());
			minBean.setIssueType(complaint.getIssueType().getName());
			minBean.setReferenceComplaint(complaint.getReferenceComplaint());
			minBean.setStatus(complaint.getStatus().getStatus());
			minBean.setSubCategory(complaint.getIssueType().getSubCategory().getName());
			minBean.setSubStatus(complaint.getSubStatus().getStatus());
			return minBean;
		}).collect(Collectors.toList());
		return complaintMinBeans;
	}
	
	public static Criteria addPageableAndSorting(Criteria criteria, Pageable page) {
		//setting page configurations
		criteria.setFirstResult(page.getPageNumber() * page.getPageSize()).setMaxResults(page.getPageSize());
		if(page.getSort()!=null) {
			Order order = page.getSort().iterator().next();
			if(!order.isAscending()) {
				criteria.addOrder(org.hibernate.criterion.Order.desc(order.getProperty()));
			} else {
				criteria.addOrder(org.hibernate.criterion.Order.asc(order.getProperty()));
			}
		}
		else {
			// set order by created date desc
			criteria.addOrder(org.hibernate.criterion.Order.desc("createdDate"));
		}
		return criteria;
	}
	
	public static Criteria addSorting(Criteria criteria, Pageable page) {
		//setting page configurations
		if(page.getSort()!=null) {
			Order order = page.getSort().iterator().next();
			if(!order.isAscending()) {
				criteria.addOrder(org.hibernate.criterion.Order.desc(order.getProperty()));
			} else {
				criteria.addOrder(org.hibernate.criterion.Order.asc(order.getProperty()));
			}
		}
		else {
			// set order by created date desc
			criteria.addOrder(org.hibernate.criterion.Order.desc("createdDate"));
		}
		return criteria;
	}
	
	public static Criteria addDateFilterCriteria(Criteria criteria, String propertyName, Date startDate, Date endDate) {
		return criteria.add(Restrictions.between(propertyName , startDate, endDate));
	}
	
	public static ComplaintRegistrationBean createComplaintRepoBean(ComplaintRegistration registration, List<AttachmentDetail> attachmentDetails) {
		ComplaintRegistrationBean bean = new ComplaintRegistrationBean();
		bean.setId(registration.getId());
		bean.setReferenceComplaint(registration.getReferenceComplaint());
		bean.setAdditionalComments(registration.getAdditionalComments());
		bean.setAreaName(registration.getArea().getName());
		bean.setComplaintBy(registration.getUser().getMobileNumber());
		bean.setComplaintLat(registration.getComplaintLat());
		bean.setComplaintLng(registration.getComplaintLng());
		bean.setEmployeeMobileNumber(registration.getEmployee().getRegisteredMobileNo());
		bean.setEmployeeName(registration.getEmployee().getName());
		bean.setIssueName(registration.getIssueType().getName());
		bean.setSubCategoryName(registration.getIssueType().getSubCategory().getName());
		bean.setCategoryName(registration.getIssueType().getSubCategory().getCategory().getName());
		bean.setIssueTitle(registration.getIssueTitle()); 
		bean.setStatus(registration.getStatus().getStatus());
		bean.setSubStatus(registration.getSubStatus().getStatus());
		bean.setDesignation(registration.getEmployee().getRole().getRoleName());
		if(registration.getEmployee().getReportingEmployee() != null ) {
			bean.setReportingEmployeeId(registration.getEmployee().getReportingEmployee().getId());
			bean.setReportingEmployeeName(registration.getEmployee().getReportingEmployee().getName());
		}
		List<String> attachmentBeans = null;
		if(attachmentDetails != null && !attachmentDetails.isEmpty()) {
			attachmentBeans = attachmentDetails.stream().map(attachmentDetail -> attachmentDetail.getName()).collect(Collectors.toList());
		}
		bean.setAttachmentsFiles(attachmentBeans);
		bean.setCreatedDate(UtilRepository.getDateInFormat(registration.getCreatedDate()));
		return bean;
	}

	public static Criteria transferToParentStatusBean(Criteria criteria) {
		return criteria.setProjection(Projections.projectionList()
				.add(Projections.property(STATUS_ALIAS + ".id"), "id")
				.add(Projections.property(STATUS_ALIAS + ".status"), "status"))
				.setResultTransformer(new AliasToBeanResultTransformer(ParentComplaintStatusBean.class));
	}
	
	public static String getDateInFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		return dateFormat.format(date);
	}
}
