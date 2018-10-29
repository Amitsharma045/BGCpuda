package com.bitGallon.complaintMgmt.repository;


import java.util.Date;

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
import com.bitGallon.complaintMgmt.bean.RemarkBean;
import com.bitGallon.complaintMgmt.bean.SubCategoryBean;


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

	public static String getIsActiveQuery(String aliasName) {
		return " " + aliasName + ".isActive = 1";
	}

	public static Criterion isActiveRestricition() {
		return Restrictions.eq("isActive", (short) 1);
	}

	public static Criteria transferToIssueTypeBean(Criteria criteria) {
		return transferToIssueTypeBean(criteria,null);
	}
	public static Criteria transferToIssueTypeBean(Criteria criteria, ProjectionList projList) {
		if(projList == null) projList = Projections.projectionList();
		return criteria.createAlias(ISSUE_TYPE_ALIAS + ".subCategory", SUB_CATEGORY_ALIAS)
				.createAlias(SUB_CATEGORY_ALIAS + ".category", CATEGORY_ALIAS)
				.setProjection(projList.add(Projections.property(ISSUE_TYPE_ALIAS + "issue.id"), "id")
						.add(Projections.property(ISSUE_TYPE_ALIAS + ".name"), "name")
						.add(Projections.property(SUB_CATEGORY_ALIAS + ".id"), "subCategoryId")
						.add(Projections.property(SUB_CATEGORY_ALIAS + ".name"), "subCategoryName")
						.add(Projections.property(CATEGORY_ALIAS + ".id"), "categoryId")
						.add(Projections.property(CATEGORY_ALIAS + ".name"), "categoryName"));
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
		return criteria
				.setProjection(Projections.projectionList().add(Projections.property(COMPLAINT_REG + ".id"), "id")
						.add(Projections.property(COMPLAINT_REG + ".name"), "name"))
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
		criteria.setProjection(projList.add(Projections.property(COMPLAINT_REG_MIN + ".issueTitle"), "issueTitle").
		add(Projections.property(USER_ALIAS + ".mobileNumber"), "complaintBy").
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
	
	public static Criteria addPageableAndSorting(Criteria criteria, Pageable page) {
		criteria.setFirstResult(page.getPageNumber()*page.getPageSize()).setMaxResults(page.getPageSize());
		if(page.getSort()!=null) {
			Order order = page.getSort().iterator().next();
			if(!order.isAscending()) {
				criteria.addOrder(org.hibernate.criterion.Order.desc(order.getProperty()));
			} else {
				criteria.addOrder(org.hibernate.criterion.Order.asc(order.getProperty()));
			}
		}
		return criteria;
	}
	
	public static Criteria addDateFilterCriteria(Criteria criteria, String propertyName, Date startDate, Date endDate) {
		return criteria.add(Restrictions.between("DATE(" + propertyName + ")", startDate, endDate));
	}
}
