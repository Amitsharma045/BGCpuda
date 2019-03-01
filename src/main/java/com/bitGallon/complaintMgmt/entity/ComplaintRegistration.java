package com.bitGallon.complaintMgmt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BG_ComplaintRegistration")
public class ComplaintRegistration extends BaseEntity<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "ComplaintId", nullable = false, unique = true)
	private String complaintId;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "AssignedTo", referencedColumnName = "id")
	private Employee employee;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name ="RaisedBy", referencedColumnName ="id")
	private User user;
//	@JoinColumn(name = "BG_ComplaintLevel", referencedColumnName = "id")
	@Column(name ="issueTitle")
	private String issueTitle;
	@Column(name = "ComplaintLevel", columnDefinition="tinyint(10) default 0")
	private Short complaintLevel;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "IssueType", referencedColumnName = "id")
	private IssueType issueType;
	@Column(name ="EscalatedTime")
	private Date escalatedTime;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "Area_id", referencedColumnName = "id")
	private Area area;
	@Column(name = "complaintLat" , precision = 10 , scale = 8 )
	private Double complaintLat;
	@Column(name = "complaintLng" , precision = 11 , scale = 8 )
	private Double complaintLng;
	@Column(name = "ReferenceComplaint", nullable = false)
	private String referenceComplaint;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "Status", referencedColumnName = "id")
	private ComplaintStatus status;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "SubStatus", referencedColumnName = "id")
	private ComplaintStatus subStatus;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "Remark", referencedColumnName = "id")
	private Remark remark;
	@Lob
	@Column(name ="AdditionalComments")
	private String additionalComments;
	
	@Column(name ="LandMark")
	private String landMark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Short getComplaintLevel() {
		return complaintLevel;
	}
	public void setComplaintLevel(Short complaintLevel) {
		this.complaintLevel = complaintLevel;
	}
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Double getComplaintLat() {
		return complaintLat;
	}
	public void setComplaintLat(Double complaintLat) {
		this.complaintLat = complaintLat;
	}
	public double getComplaintLng() {
		return complaintLng;
	}
	public String getReferenceComplaint() {
		return referenceComplaint;
	}
	public void setReferenceComplaint(String referenceComplaint) {
		this.referenceComplaint = referenceComplaint;
	}
	public ComplaintStatus getStatus() {
		return status;
	}
	public void setStatus(ComplaintStatus status) {
		this.status = status;
	}
	public ComplaintStatus getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(ComplaintStatus subStatus) {
		this.subStatus = subStatus;
	}
	public Remark getRemark() {
		return remark;
	}
	public void setRemark(Remark remark) {
		this.remark = remark;
	}
	public String getAdditionalComments() {
		return additionalComments;
	}
	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getIssueTitle() {
		return issueTitle;
	}
	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public Date getEscalatedTime() {
		return escalatedTime;
	}
	public void setEscalatedTime(Date escalatedTime) {
		this.escalatedTime = escalatedTime;
	}
	public void setComplaintLng(Double complaintLng) {
		this.complaintLng = complaintLng;
	}
}
