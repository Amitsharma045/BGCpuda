package com.bitGallon.complaintMgmt.entity;

import java.io.Serializable;

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
public class ComplaintRegistration extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "ComplaintId", nullable = false, unique = true)
	private String complaintId;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "AssignedTo", referencedColumnName = "id")
	private Employee employee;
//	@JoinColumn(name = "BG_ComplaintLevel", referencedColumnName = "id")
	@Column(name = "ComplaintLevel")
	private short complaintLevel;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "IssueType", referencedColumnName = "id")
	private IssueType issueType;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "Area_id", referencedColumnName = "id")
	private Area area;
	@Column(name = "complaintLat" , precision = 10 , scale = 8 )
	private double complaintLat;
	@Column(name = "complaintLng" , precision = 11 , scale = 8 )
	private double complaintLng;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "ReferenceComplaint", referencedColumnName = "id")
	private ComplaintRegistration referenceComplaint;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public short getComplaintLevel() {
		return complaintLevel;
	}
	public void setComplaintLevel(short complaintLevel) {
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
	public ComplaintRegistration getReferenceComplaint() {
		return referenceComplaint;
	}
	public void setReferenceComplaint(ComplaintRegistration referenceComplaint) {
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
}
