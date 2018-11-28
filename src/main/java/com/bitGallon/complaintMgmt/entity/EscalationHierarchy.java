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
@Table(name = "BG_EscalationHierarchy")
public class EscalationHierarchy extends BaseEntity<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "issueTypeId", referencedColumnName = "id")
	private IssueType issueType;
	@Column(name = "level", nullable = false)
	private short level;
	@Column(name = "EscalationTime", nullable = false)
	private short escalationTime;
	
	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public short getEscalationTime() {
		return escalationTime;
	}

	public void setEscalationTime(short escalationTime) {
		this.escalationTime = escalationTime;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
