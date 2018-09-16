package com.bitGallon.complaintMgmt.bean;


public class EscalationHierarchyBean {
	private long id;
	private String name;
	private CategoryBean category;

	public CategoryBean getCategory() {
		return category;
	}

	public void setCategory(CategoryBean category) {
		this.category = category;
	}

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

}
