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
@Table(name = "BG_Employee")
public class Employee extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Name", nullable = false)
	private String name;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BG_RoleId", referencedColumnName = "id")
	private Role role;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BG_CategoryId", referencedColumnName = "id")
	private Category category;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BG_ReportingId", referencedColumnName = "id")
	private Employee reportingEmployee;
	@Column(name = "RegisteredMobileNo", nullable = false, unique = true)
	private String registeredMobileNo;
	@Column(name = "AlternateMobileNo", nullable = false)
	private String alternateMobileNo;
	@Column(name = "EmailId", nullable = false, unique = true)
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Employee getReportingEmployee() {
		return reportingEmployee;
	}
	public void setReportingEmployee(Employee reportingEmployee) {
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
