package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.IssueType;

@Repository
@Transactional
public class IssueTypeRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Long saveIssueType(IssueType issueType) throws Exception {
		Long id = (Long) getSession().save(issueType);
		return id;
	}

	@SuppressWarnings("unchecked")
	public IssueType getIssueType(long id) {
		 List<IssueType> issueTypes =  getSession()
				.createQuery("FROM IssueType iT WHERE iT.id =:id AND " + UtilRepository.getIsActiveQuery("iT"))
				.setParameter("id", id).list();
		if (issueTypes.size() == 1) return issueTypes.get(0);
		else return null;
	}

	@SuppressWarnings("unchecked")
	public List<IssueType> getAllIssueTypes() {
		return (List<IssueType>) getSession()
				.createQuery("FROM IssueType iT WHERE " + UtilRepository.getIsActiveQuery("iT")).list();
	}
}
