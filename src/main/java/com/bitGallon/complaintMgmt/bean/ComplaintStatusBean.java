package com.bitGallon.complaintMgmt.bean;

public class ComplaintStatusBean{
	private long id;
	private String status;
	private long parentStatusId ;
	private String parentStatus ;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentStatusId() {
		return parentStatusId;
	}
	public void setParentStatusId(long parentStatusId) {
		this.parentStatusId = parentStatusId;
	}
	public String getParentStatus() {
		return parentStatus;
	}
	public void setParentStatus(String parentStatus) {
		this.parentStatus = parentStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
