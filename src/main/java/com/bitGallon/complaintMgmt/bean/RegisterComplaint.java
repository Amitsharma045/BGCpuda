package com.bitGallon.complaintMgmt.bean;

import java.util.List;

public class RegisterComplaint{
	String mobileNumber;
	List<CategoryBean> categoryBean;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public List<CategoryBean> getCategoryBean() {
		return categoryBean;
	}
	public void setCategoryBean(List<CategoryBean> categoryBean) {
		this.categoryBean = categoryBean;
	}
}
