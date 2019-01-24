package com.bitGallon.complaintMgmt.bean;

import java.util.List;

public class MinCategorySubCategoryBean {
	/**
	 * 
	 */
	private long categoryId;
	private String categoryName;
	private List<MinSubCategoryBean> subCategories;
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
	public List<MinSubCategoryBean> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<MinSubCategoryBean> subCategories) {
		this.subCategories = subCategories;
	}
}
