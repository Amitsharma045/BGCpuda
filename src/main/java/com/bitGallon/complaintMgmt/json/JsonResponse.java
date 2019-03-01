package com.bitGallon.complaintMgmt.json;

import java.util.List;

import com.bitGallon.complaintMgmt.bean.CategoryBean;
import com.bitGallon.complaintMgmt.bean.ComplaintRegistrationBean;
import com.bitGallon.complaintMgmt.bean.ComplaintStatusBean;
import com.bitGallon.complaintMgmt.bean.IssueTypeBean;
import com.bitGallon.complaintMgmt.bean.MinCategorySubCategoryBean;
import com.bitGallon.complaintMgmt.bean.ParentComplaintStatusBean;
import com.bitGallon.complaintMgmt.bean.RemarkBean;
import com.bitGallon.complaintMgmt.bean.SubCategoryBean;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;

/**
 * @author aksharma
 *
 */
public class JsonResponse {
	
	private String statusCode;
	private String message;
	private String accessToken;
	private String refreshToken;
	private ComplaintRegistrationBean complaintRegistrationBean;
	private List<ComplaintRegistration> complaintList;
	private ComplaintRegistration complaintRegistration;
	private CategoryBean categoryBean;
	private List<CategoryBean> categoryBeanList;
	private IssueTypeBean issueTypeBean;
	private List<IssueTypeBean> issueTypeBeanList;
	private SubCategoryBean subCategoryBean;
	private List<SubCategoryBean> subCategoryBeanList;
	private ComplaintStatusBean complaintStatusBean;
	private List<ComplaintStatusBean> complaintStatusBeanList;
	private RemarkBean remarkBean;
	private List<RemarkBean> remarkBeanList;
	private List<ParentComplaintStatusBean> statusBeans;
	private MinCategorySubCategoryBean categorySubCateogryBean;
	private List<MinCategorySubCategoryBean> categorySubCateogryList;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public ComplaintRegistrationBean getComplaintRegistrationBean() {
		return complaintRegistrationBean;
	}
	public void setComplaintRegistrationBean(ComplaintRegistrationBean complaintRegistrationBean) {
		this.complaintRegistrationBean = complaintRegistrationBean;
	}
	public List<ComplaintRegistration> getComplaintList() {
		return complaintList;
	}
	public void setComplaintList(List<ComplaintRegistration> complaintList) {
		this.complaintList = complaintList;
	}
	public CategoryBean getCategoryBean() {
		return categoryBean;
	}
	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}
	public List<CategoryBean> getCategoryBeanList() {
		return categoryBeanList;
	}
	public void setCategoryBeanList(List<CategoryBean> categoryBeanList) {
		this.categoryBeanList = categoryBeanList;
	}
	public ComplaintRegistration getComplaintRegistration() {
		return complaintRegistration;
	}
	public void setComplaintRegistration(ComplaintRegistration complaintRegistration) {
		this.complaintRegistration = complaintRegistration;
	}
	public IssueTypeBean getIssueTypeBean() {
		return issueTypeBean;
	}
	public void setIssueTypeBean(IssueTypeBean issueTypeBean) {
		this.issueTypeBean = issueTypeBean;
	}
	public List<IssueTypeBean> getIssueTypeBeanList() {
		return issueTypeBeanList;
	}
	public void setIssueTypeBeanList(List<IssueTypeBean> issueTypeBeanList) {
		this.issueTypeBeanList = issueTypeBeanList;
	}
	public SubCategoryBean getSubCategoryBean() {
		return subCategoryBean;
	}
	public void setSubCategoryBean(SubCategoryBean subCategoryBean) {
		this.subCategoryBean = subCategoryBean;
	}
	public List<SubCategoryBean> getSubCategoryBeanList() {
		return subCategoryBeanList;
	}
	public void setSubCategoryBeanList(List<SubCategoryBean> subCategoryBeanList) {
		this.subCategoryBeanList = subCategoryBeanList;
	}
	public ComplaintStatusBean getComplaintStatusBean() {
		return complaintStatusBean;
	}
	public void setComplaintStatusBean(ComplaintStatusBean complaintStatusBean) {
		this.complaintStatusBean = complaintStatusBean;
	}
	public List<ComplaintStatusBean> getComplaintStatusBeanList() {
		return complaintStatusBeanList;
	}
	public void setComplaintStatusBeanList(List<ComplaintStatusBean> complaintStatusBeanList) {
		this.complaintStatusBeanList = complaintStatusBeanList;
	}
	public RemarkBean getRemarkBean() {
		return remarkBean;
	}
	public void setRemarkBean(RemarkBean remarkBean) {
		this.remarkBean = remarkBean;
	}
	public List<RemarkBean> getRemarkBeanList() {
		return remarkBeanList;
	}
	public void setRemarkBeanList(List<RemarkBean> remarkBeanList) {
		this.remarkBeanList = remarkBeanList;
	}
	public List<ParentComplaintStatusBean> getStatusBeans() {
		return statusBeans;
	}
	public void setStatusBeans(List<ParentComplaintStatusBean> statusBeans) {
		this.statusBeans = statusBeans;
	}
	public MinCategorySubCategoryBean getCategorySubCateogryBean() {
		return this.categorySubCateogryBean;
	}

	public void setCategorySubCateogryBean(MinCategorySubCategoryBean categorySubCateogryBean) {
		this.categorySubCateogryBean = categorySubCateogryBean;
	}

	public List<MinCategorySubCategoryBean> getCategorySubCateogryList() {
		return this.categorySubCateogryList;
	}

	public void setCategorySubCateogryList(List<MinCategorySubCategoryBean> categorySubCateogryList) {
		this.categorySubCateogryList = categorySubCateogryList;
	}
}
