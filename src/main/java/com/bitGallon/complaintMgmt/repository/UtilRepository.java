package com.bitGallon.complaintMgmt.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import com.bitGallon.complaintMgmt.bean.CategoryBean;
import com.bitGallon.complaintMgmt.bean.ComplaintStatusBean;
import com.bitGallon.complaintMgmt.bean.IssueTypeBean;
import com.bitGallon.complaintMgmt.bean.RemarkBean;
import com.bitGallon.complaintMgmt.bean.SubCategoryBean;

public class UtilRepository {
	public static final String ISSUE_TYPE_ALIAS = "ISSUETYPE";
	public static final String SUB_CATEGORY_ALIAS = "SUBCATEGORY";
	public static final String CATEGORY_ALIAS = "CATEGORY";
	public static final String REMARK_ALIAS = "REMARK";
	public static final String STATUS_ALIAS = "STATUS";
	public static final String PARENT_STATUS_ALIAS = "PARENT_STATUS";

	public static String getIsActiveQuery(String aliasName) {
		return " " + aliasName + ".isActive = 1";
	}

	public static Criterion isActiveRestricition() {
		return Restrictions.eq("isActive", (short) 1);
	}

	public static Criteria transferToIssueTypeBean(Criteria criteria) {
		return criteria.createAlias(ISSUE_TYPE_ALIAS + ".subCategory", SUB_CATEGORY_ALIAS)
				.createAlias(SUB_CATEGORY_ALIAS + ".category", CATEGORY_ALIAS)
				.setProjection(Projections.projectionList().add(Projections.property(ISSUE_TYPE_ALIAS + ".id"), "id")
						.add(Projections.property(ISSUE_TYPE_ALIAS + ".name"), "name")
						.add(Projections.property(SUB_CATEGORY_ALIAS + ".id"), "subCategoryId")
						.add(Projections.property(SUB_CATEGORY_ALIAS + ".name"), "subCategoryName")
						.add(Projections.property(CATEGORY_ALIAS + ".id"), "categoryId")
						.add(Projections.property(CATEGORY_ALIAS + ".name"), "categoryName"))
				.setResultTransformer(new AliasToBeanResultTransformer(IssueTypeBean.class));
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
}
