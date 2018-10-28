package com.bitGallon.complaintMgmt.bean;

public class ComplaintMinBean {
	private String employeeName;
	private String employeeNo;
	private String issueType;
	private String subCategory;
	private String category;
	private String referenceComplaint;
	private String status;
	private String subStatus;
	private String issueTitle;
	private String complaintBy;
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getReferenceComplaint() {
		return referenceComplaint;
	}
	public void setReferenceComplaint(String referenceComplaint) {
		this.referenceComplaint = referenceComplaint;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}
	public String getIssueTitle() {
		return issueTitle;
	}
	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}
	public String getComplaintBy() {
		return complaintBy;
	}
	public void setComplaintBy(String complaintBy) {
		this.complaintBy = complaintBy;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}