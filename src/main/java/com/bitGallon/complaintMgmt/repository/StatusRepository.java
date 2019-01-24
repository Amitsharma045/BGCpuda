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
import com.bitGallon.complaintMgmt.bean.ParentComplaintStatusBean;
import com.bitGallon.complaintMgmt.entity.ComplaintStatus;

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
		criteria.add(Restrictions.eq("id", id)).add(Restrictions.isNull("parentStatus.id")).
		add(UtilRepository.isActiveRestricition());
		List<ComplaintStatusBean> statusBeans = UtilRepository.transferToStatusBean(criteria).list();
		if(statusBeans.isEmpty()) return null;
		return statusBeans.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public ComplaintStatus getStatus(String name) {
		Criteria criteria = getSession().createCriteria(ComplaintStatus.class,  UtilRepository.STATUS_ALIAS);
		criteria.add(Restrictions.eq("status", name)).add(UtilRepository.isActiveRestricition());
		List<ComplaintStatus> statusBeans =criteria.list();
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
	public List<ParentComplaintStatusBean> getAllParentStatuses() {
		Criteria criteria = getSession().createCriteria(ComplaintStatus.class, UtilRepository.STATUS_ALIAS)
				.add(Restrictions.isNull("parentStatus.id")).add(UtilRepository.isActiveRestricition());
		return UtilRepository.transferToParentStatusBean(criteria).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ComplaintStatusBean> getAllStatuses(long parentId) {
		Criteria criteria = getSession().createCriteria(ComplaintStatus.class, UtilRepository.STATUS_ALIAS)
				.add(Restrictions.eq("parentStatus.id", parentId))
				.add(UtilRepository.isActiveRestricition());
		return UtilRepository.transferToStatusBean(criteria).list();
	}
	
	public ComplaintStatusBean updateIsActive(long id, short isActive) {
		ComplaintStatus complaintStatus= getSession().byId(ComplaintStatus.class).load(id);
		if(complaintStatus != null) {
			complaintStatus.setIsActive(isActive);
			getSession().update(complaintStatus);
			return getStatus(id);
		}
		return null;
	}

	public ComplaintStatus getSubStatus(String name) {
		Criteria criteria = getSession().createCriteria(ComplaintStatus.class,  UtilRepository.STATUS_ALIAS);
		criteria.add(Restrictions.eq("status", name)).add(Restrictions.isNotNull("parentStatus.id")).add(UtilRepository.isActiveRestricition());
		List<ComplaintStatus> statusBeans =criteria.list();
		if(statusBeans.isEmpty()) return null;
		return statusBeans.get(0);
	}
}
