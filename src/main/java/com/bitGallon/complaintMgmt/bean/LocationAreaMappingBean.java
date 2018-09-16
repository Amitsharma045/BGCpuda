package com.bitGallon.complaintMgmt.bean;

public class LocationAreaMappingBean {
	/**
	 * 
	 */
	private long id;
	private String keyName;
	private String LableName;
	private AreaBean area;

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

	public AreaBean getArea() {
		return area;
	}

	public void setArea(AreaBean area) {
		this.area = area;
	}

}
