package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
	public ComplaintStatus getStatus(long id) {
		List<ComplaintStatus> complaintStatus = getSession()
				.createQuery("FROM ComplaintStatus s WHERE s.id =:id AND " + UtilRepository.getIsActiveQuery("s"))
				.setParameter("id", id).list();
		if (complaintStatus.size() == 1) return complaintStatus.get(0);
		else return null;
	}

	@SuppressWarnings("unchecked")
	public List<ComplaintStatus> getAllStatuses() {
		return (List<ComplaintStatus>) getSession()
				.createQuery("FROM ComplaintStatus s WHERE " + UtilRepository.getIsActiveQuery("s")).list();
	}
}
