package com.bitGallon.complaintMgmt.bean;

public class EmployeeBean{
	private long id;
	private String name;
	private RoleBean role;
	private String registeredMobileNo;
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
	public String getRegisteredMobileNo() {
		return registeredMobileNo;
	}
	public void setRegisteredMobileNo(String registeredMobileNo) {
		this.registeredMobileNo = registeredMobileNo;
	}
}
