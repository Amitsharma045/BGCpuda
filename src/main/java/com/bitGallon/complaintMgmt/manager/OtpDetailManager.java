package com.bitGallon.complaintMgmt.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitGallon.complaintMgmt.entity.OtpTransectionDetail;
import com.bitGallon.complaintMgmt.repository.OtpTransectionRepository;


/**
 * @author rpsingh
 *
 */
@Service
public class OtpDetailManager {

	@Autowired
	private  OtpTransectionRepository otpTransectionDetailDAO;

	public Long saveOtpDetails(OtpTransectionDetail otp) throws Exception {
		return otpTransectionDetailDAO.saveOtpDetails(otp);
	}
	
	public OtpTransectionDetail getOtpDetails(String otp,String mobileNumber) throws Exception {
		return otpTransectionDetailDAO.getOtpDetails(otp, mobileNumber);
	}
	
	public  boolean delete(OtpTransectionDetail otpTransectionDetail) throws Exception {
		return otpTransectionDetailDAO.delete(otpTransectionDetail);
	}
}
