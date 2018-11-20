package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.IssueTypeBean;
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
	public IssueType getIssueType(String issueId) throws Exception {
		List<IssueType> issueTypelist = getSession()
				.createQuery("FROM IssueType it WHERE it.id =:p1")
				.setParameter("p1", Long.valueOf(issueId)).list();
		if(issueTypelist.size() != 0) {
			return issueTypelist.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public IssueTypeBean getIssueType(long id) {
		Criteria criteria = getSession().createCriteria(IssueType.class,  UtilRepository.ISSUE_TYPE_ALIAS);
		criteria.add(Restrictions.eq("id", id)).add(UtilRepository.isActiveRestricition());
		List<IssueTypeBean> issueTypes =UtilRepository.transferToIssueTypeBean(criteria).list();
		if(issueTypes.isEmpty()) return null;
		return issueTypes.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<IssueTypeBean> getAllIssueTypes() {
		Criteria criteria = getSession().createCriteria(IssueType.class, UtilRepository.ISSUE_TYPE_ALIAS)
				.add(UtilRepository.isActiveRestricition());
		return UtilRepository.transferToIssueTypeBean(criteria).list();
	}

	/**
	 * Get all the issue types for given subcategory id
	 * @param subCategoryId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<IssueTypeBean> getAllIssueTypes(long subCategoryId){
		Criteria criteria = getSession().createCriteria(IssueType.class, UtilRepository.ISSUE_TYPE_ALIAS);
		criteria.add(Restrictions.eq("subCategory.id", subCategoryId)).add(UtilRepository.isActiveRestricition());
		return UtilRepository.transferToIssueTypeBean(criteria).list();
	}
	
	public IssueTypeBean updateIsActive(long id, short isActive) {
		IssueType issueType= getSession().byId(IssueType.class).load(id);
		if(issueType != null) {
			issueType.setIsActive(isActive);
			getSession().update(issueType);
			return getIssueType(id);
		}
		return null;
	}
}
