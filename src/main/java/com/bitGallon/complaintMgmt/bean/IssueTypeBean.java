package com.bitGallon.complaintMgmt.bean;

public class IssueTypeBean {
	/**
	 * 
	 */
	private long id;
	private String name;
	private long subCategoryId;
	private String subCategoryName;
	private long categoryId;
	private String categoryName;

	//	private CategoryBean category;
	
	public IssueTypeBean() {}
	
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

	public long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

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

	@Override
	public String toString() {
		return "IssueTypeBean [id=" + id + ", name=" + name + ", subCategoryId=" + subCategoryId + ", subCategoryName="
				+ subCategoryName + ", categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

	

	
}
