package com.bitGallon.complaintMgmt.bean;

public class SubCategoryBean {
	/**
	 * 
	 */
	private long id;
	private String name;
	private long categoryId;
	private String categoryName;
	
	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	@Override
	public String toString() {
		return "SubCategoryBean [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", categoryName="
				+ categoryName + "]";
	}

}
