package com.bitGallon.complaintMgmt.bean;

public class ComplaintRegistrationBean {
	private long id;
	private String complaintId;
	private String employeeName;
	private String employeeMobileNumber;
	private short complaintLevel;
	private String issueName;
	private String subCategoryName;
	private String categoryName;
	private String areaName;
	private double complaintLat;
	private double complaintLng;
	private String referenceComplaint;
	private String status;
	private String subStatus;
	private String remark;
	private String additionalComments;
	private String complaintBy;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
}
