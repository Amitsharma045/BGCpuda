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
	private  OtpTransectionRepository repository;

	public Long saveOtpDetails(OtpTransectionDetail otp) throws Exception {
		return repository.saveOtpDetails(otp);
	}
	
	public OtpTransectionDetail getOtpDetails(String otp,String mobileNumber) throws Exception {
		return repository.getOtpDetails(otp, mobileNumber);
	}
	
	public  boolean delete(OtpTransectionDetail otpTransectionDetail) throws Exception {
		return repository.delete(otpTransectionDetail);
	}
}
