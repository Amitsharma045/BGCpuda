package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.EscalationHierarchy;
import com.bitGallon.complaintMgmt.entity.IssueType;

@Repository
@Transactional
public class EscalationHierarchyRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public EscalationHierarchy getEscalationHierarchyDetail(IssueType issueType, short complaintLevel) {
		List<EscalationHierarchy> escalationHierarchylist = getSession()
				.createQuery("FROM EscalationHierarchy eh WHERE eh.issueType.id =:p1 and level =:p2")
				.setParameter("p1", issueType.getId())
				.setParameter("p2", complaintLevel).list();
		if(escalationHierarchylist.size() != 0) {
			return escalationHierarchylist.get(0);
		}
		return null;
	
	}
}
