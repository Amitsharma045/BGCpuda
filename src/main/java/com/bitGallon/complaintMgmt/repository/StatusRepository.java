package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.ComplaintStatusBean;
import com.bitGallon.complaintMgmt.bean.IssueTypeBean;
import com.bitGallon.complaintMgmt.entity.ComplaintStatus;
import com.bitGallon.complaintMgmt.entity.IssueType;
import com.bitGallon.complaintMgmt.entity.Remark;

@Repository
@Transactional
public class StatusRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Long saveStatus(ComplaintStatus status) throws Exception {
		Long id = (Long) getSession().save(status);
		return id;
	}

	@SuppressWarnings("unchecked")
	public ComplaintStatusBean getStatus(long id) {
		Criteria criteria = getSession().createCriteria(ComplaintStatus.class,  UtilRepository.STATUS_ALIAS);
		criteria.add(Restrictions.eq("id", id)).add(UtilRepository.isActiveRestricition());
		List<ComplaintStatusBean> statusBeans = UtilRepository.transferToStatusBean(criteria).list();
		if(statusBeans.isEmpty()) return null;
		return statusBeans.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<ComplaintStatusBean> getAllStatuses() {
		Criteria criteria = getSession().createCriteria(ComplaintStatus.class, UtilRepository.STATUS_ALIAS)
				.add(UtilRepository.isActiveRestricition());
		return UtilRepository.transferToStatusBean(criteria).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ComplaintStatusBean> getAllStatuses(long parentId) {
		Criteria criteria = getSession().createCriteria(ComplaintStatus.class, UtilRepository.STATUS_ALIAS)
				.add(Restrictions.eq("parentStatus.id", parentId))
				.add(UtilRepository.isActiveRestricition());
		return UtilRepository.transferToStatusBean(criteria).list();
	}
}
