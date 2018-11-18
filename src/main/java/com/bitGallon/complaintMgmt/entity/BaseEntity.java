package com.bitGallon.complaintMgmt.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author aksharma
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<U> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CreatedDate", updatable = false)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "ModifiedDate", nullable = false)
	@UpdateTimestamp
	private Date modifiedDate;

	@Column(name = "ModifiedBy")
	@LastModifiedBy
	private U modifiedBy;

	// 1 is Active, 0 inactive
	@Column(name = "IsActive", columnDefinition="tinyint(10) default 1", nullable = false)
	private short isActive;

	public short getIsActive() {
		return isActive;
	}

	public void setIsActive(short isActive) {
		this.isActive = isActive;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public U getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(U modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	@PreUpdate
	public void setModifiedDateUpdate() {
		this.modifiedDate = new Date();
	}

}
