package com.bitGallon.complaintMgmt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "BG_LocationAreaMapping")
public class LocationAreaMapping extends BaseEntity<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "KeyName", nullable = false)
	private String keyName;
	@Column(name = "LableName", nullable = false)
	private String LableName;
	@JoinColumn(name = "BG_AreaId", referencedColumnName = "id")
	private Area area;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getLableName() {
		return LableName;
	}

	public void setLableName(String lableName) {
		LableName = lableName;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
