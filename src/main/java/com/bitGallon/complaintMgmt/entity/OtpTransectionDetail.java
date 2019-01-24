package com.bitGallon.complaintMgmt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rpsingh
 *
 */
@Entity
@Table(name = "BG_Otp")
public class OtpTransectionDetail extends BaseEntity<OtpTransectionDetail>  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "OTD_MobileNumber")
	private String mobileNumber;
	@Column(name = "OTD_EmailId")
	private String emailId;
	@Column(name = "OTD_otp")
	private String otp;
	@Column(name = "OTD_ExpirytimeStamp")
	private Date expirytimeStamp;
	@Column(name = "OTD_UserType")
	private String userType;

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Date getExpirytimeStamp() {
		return expirytimeStamp;
	}
	public void setExpirytimeStamp(Date expirytimeStamp) {
		this.expirytimeStamp = expirytimeStamp;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}


}
