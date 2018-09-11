package com.bitGallon.complaintMgmt.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BG_ComplaintStatus")
public class ComplaintStatus extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Status", nullable = false)
	private String Status;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BG_StatusId", referencedColumnName = "id")
	private ComplaintStatus parentStatusId ;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public ComplaintStatus getParentStatusId() {
		return parentStatusId;
	}
	public void setParentStatusId(ComplaintStatus parentStatusId) {
		this.parentStatusId = parentStatusId;
	}
}
