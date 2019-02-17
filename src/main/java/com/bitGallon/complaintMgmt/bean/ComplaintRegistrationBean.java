package com.bitGallon.complaintMgmt.bean;

import java.util.List;

public class ComplaintRegistrationBean {
	private long id;
	private String employeeName;
	private Long reportingEmployeeId;
	private String reportingEmployeeName;
	private String employeeMobileNumber;
	private String designation;
	private short complaintLevel;
	private String issueName;
	private String subCategoryName;
	private String categoryName;
	private String issueTitle;
	private String areaName;
	private double complaintLat;
	private double complaintLng;
	private String referenceComplaint;
	private String status;
	private String subStatus;
	private String additionalComments;
	private String complaintBy;
	private List<String> attachmentsFiles;
	private String createdDate;
	
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getIssueTitle() {
		return issueTitle;
	}
	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}
	
	public List<String> getAttachmentsFiles() {
		return attachmentsFiles;
	}
	public void setAttachmentsFiles(List<String> attachmentsFiles) {
		this.attachmentsFiles = attachmentsFiles;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeMobileNumber() {
		return employeeMobileNumber;
	}
	public void setEmployeeMobileNumber(String employeeMobileNumber) {
		this.employeeMobileNumber = employeeMobileNumber;
	}
	public short getComplaintLevel() {
		return complaintLevel;
	}
	public void setComplaintLevel(short complaintLevel) {
		this.complaintLevel = complaintLevel;
	}
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public double getComplaintLat() {
		return complaintLat;
	}
	public void setComplaintLat(double complaintLat) {
		this.complaintLat = complaintLat;
	}
	public double getComplaintLng() {
		return complaintLng;
	}
	public void setComplaintLng(double complaintLng) {
		this.complaintLng = complaintLng;
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
	public String getAdditionalComments() {
		return additionalComments;
	}
	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}
	public String getComplaintBy() {
		return complaintBy;
	}
	public void setComplaintBy(String complaintBy) {
		this.complaintBy = complaintBy;
	}
	public Long getReportingEmployeeId() {
		return reportingEmployeeId;
	}
	public void setReportingEmployeeId(Long reportingEmployeeId) {
		this.reportingEmployeeId = reportingEmployeeId;
	}
	public String getReportingEmployeeName() {
		return reportingEmployeeName;
	}
	public void setReportingEmployeeName(String reportingEmployeeName) {
		this.reportingEmployeeName = reportingEmployeeName;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
