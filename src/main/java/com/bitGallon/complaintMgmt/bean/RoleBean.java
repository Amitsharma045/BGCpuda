package com.bitGallon.complaintMgmt.bean;

public class RoleBean {
	/**
	 * 
	 */
	private long id;
	private String roleName;
	private AreaBean area;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public AreaBean getArea() {
		return area;
	}
	public void setArea(AreaBean area) {
		this.area = area;
	}

}
