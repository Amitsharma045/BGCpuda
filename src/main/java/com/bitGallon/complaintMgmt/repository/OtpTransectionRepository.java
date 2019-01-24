package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.OtpTransectionDetail;

/**
 * @author rpsingh
 *
 */

@Repository
@Transactional
public class OtpTransectionRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Long saveOtpDetails(OtpTransectionDetail otp) throws Exception {
		Long id = (Long) getSession().save(otp);
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public OtpTransectionDetail getOtpDetails(String otp, String mobileNumber) throws Exception {
		List<OtpTransectionDetail> otpTransectionDetailslist = getSession()
															  .createQuery("FROM OtpTransectionDetail od WHERE od.mobileNumber=:p1 AND od.otp=:p2 order by createdDate desc")
															  .setParameter("p1", mobileNumber)
															  .setParameter("p2", otp).list();

		if(otpTransectionDetailslist.size() != 0) {
			return otpTransectionDetailslist.get(0);
		}
		return null;
	}
	
	public  boolean delete(OtpTransectionDetail otpTransectionDetail) throws Exception {
		getSession().delete(otpTransectionDetail);
		return true;
	}
}
