package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.CategoryBean;
import com.bitGallon.complaintMgmt.bean.ComplaintRegistrationBean;
import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;

@Repository
@Transactional
public class ComplaintRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Long saveComplaintRegistration(ComplaintRegistration complaintRegistration) throws Exception {
		return (Long) getSession().save(complaintRegistration);
	}

	/*@SuppressWarnings("unchecked")
	public ComplaintRegistration getCategory(long id) {
		Criteria criteria = getSession().createCriteria(ComplaintRegistration.class, UtilRepository.COMPLAINT_REG);
		criteria.add(Restrictions.eq("id", id)).add(UtilRepository.isActiveRestricition());
		List<ComplaintRegistration> categoryBeans = UtilRepository.transferToComplaintRegistration(criteria).list();
		if(categoryBeans.isEmpty()) return null;
		return categoryBeans.get(0);
	}*/

	@SuppressWarnings("unchecked")
	public List<ComplaintRegistration> getAllCategory(Pageable pageable) {
		Criteria criteria = getSession().createCriteria(ComplaintRegistration.class, UtilRepository.CATEGORY_ALIAS);
		criteria.add(UtilRepository.isActiveRestricition());
		Page<ComplaintRegistration> asd = (Page<ComplaintRegistration>) UtilRepository.transferToCategoryBean(criteria).list();
		return asd.getContent();
	}

	public boolean updateIsActive(long id, short isActive) {
		ComplaintRegistration complaint = getSession().byId(ComplaintRegistration.class).load(id);
		if(complaint != null) {
			complaint.setIsActive((short) (isActive == 0 ? 0 : 1) );
			getSession().update(complaint);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<ComplaintRegistration> getAllComplaintsForUser(Pageable page, long mobileNo) {
		Criteria criteria = getSession().createCriteria(ComplaintRegistration.class, UtilRepository.COMPLAINT_REG_MIN);
		criteria.add(UtilRepository.isActiveRestricition());
		UtilRepository.addPageableAndSorting(criteria, page);
		criteria.add(Restrictions.eq(UtilRepository.USER_ALIAS+".mobileNumber", mobileNo+""));
		return UtilRepository.transferToMiniComplaintBean(criteria).list(); 
	}

	public ComplaintRegistration getComplaintForUser(String complanintId, long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ComplaintRegistration getComplaintByComplaintNumber(String complaintNumber) {
		List<ComplaintRegistration> complaintlist = getSession()
				.createQuery("FROM ComplaintRegistration cr WHERE cr.complaintId =:p1")
				.setParameter("p1", complaintNumber).list();
		if(complaintlist.size() != 0) {
			return complaintlist.get(0);
		}
		return null;
	}
}
