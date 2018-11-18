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
public class ComplaintStatus extends BaseEntity<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Status", nullable = false)
	private String status;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BG_StatusId", referencedColumnName = "id")
	private ComplaintStatus parentStatus;
	public long getId() {
		return id;
	}
	public ComplaintStatus getParentStatus() {
		return parentStatus;
	}
	public void setParentStatus(ComplaintStatus parentStatus) {
		this.parentStatus = parentStatus;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
