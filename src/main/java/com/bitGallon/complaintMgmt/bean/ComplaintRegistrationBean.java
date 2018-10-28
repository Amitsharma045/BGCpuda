package com.bitGallon.complaintMgmt.bean;

public class ComplaintRegistrationBean {
	private long id;
	private String complaintId;
	private EmployeeBean employee;
	private short complaintLevel;
	private IssueTypeBean issueType;
	private AreaBean area;
	private double complaintLat;
	private double complaintLng;
	private ComplaintRegistrationBean referenceComplaint;
	private ComplaintStatusBean status;
	private ComplaintStatusBean subStatus;
	private RemarkBean remark;
	private String additionalComments;
	private UserBean complaintBy;
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
	public EmployeeBean getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}
	public short getComplaintLevel() {
		return complaintLevel;
	}
	public void setComplaintLevel(short complaintLevel) {
		this.complaintLevel = complaintLevel;
	}
	public IssueTypeBean getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueTypeBean issueType) {
		this.issueType = issueType;
	}
	public AreaBean getArea() {
		return area;
	}
	public void setArea(AreaBean area) {
		this.area = area;
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
	public ComplaintRegistrationBean getReferenceComplaint() {
		return referenceComplaint;
	}
	public void setReferenceComplaint(ComplaintRegistrationBean referenceComplaint) {
		this.referenceComplaint = referenceComplaint;
	}
	public ComplaintStatusBean getStatus() {
		return status;
	}
	public void setStatus(ComplaintStatusBean status) {
		this.status = status;
	}
	public ComplaintStatusBean getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(ComplaintStatusBean subStatus) {
		this.subStatus = subStatus;
	}
	public RemarkBean getRemark() {
		return remark;
	}
	public void setRemark(RemarkBean remark) {
		this.remark = remark;
	}
	public String getAdditionalComments() {
		return additionalComments;
	}
	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}
	public UserBean getComplaintBy() {
		return complaintBy;
	}
	public void setComplaintBy(UserBean complaintBy) {
		this.complaintBy = complaintBy;
	}
	
}
