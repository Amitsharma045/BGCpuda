package com.bitGallon.complaintMgmt.bean;

public class EmployeeBean{
	private long id;
	private String name;
	private RoleBean role;
	private EmployeeBean reportingEmployee;
	private String registeredMobileNo;
	private String alternateMobileNo;
	private String emailId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RoleBean getRole() {
		return role;
	}
	public void setRole(RoleBean role) {
		this.role = role;
	}
	public EmployeeBean getReportingEmployee() {
		return reportingEmployee;
	}
	public void setReportingEmployee(EmployeeBean reportingEmployee) {
		this.reportingEmployee = reportingEmployee;
	}
	public String getRegisteredMobileNo() {
		return registeredMobileNo;
	}
	public void setRegisteredMobileNo(String registeredMobileNo) {
		this.registeredMobileNo = registeredMobileNo;
	}
	public String getAlternateMobileNo() {
		return alternateMobileNo;
	}
	public void setAlternateMobileNo(String alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
