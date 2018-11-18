package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.RemarkBean;
import com.bitGallon.complaintMgmt.entity.Remark;

@Repository
@Transactional
public class RemarkRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public Long saveRemark(Remark remark) throws Exception {
		Long id = (Long) getSession().save(remark);
		return id;
	}
	
	public RemarkBean getRemark(long id) {
		Criteria criteria = getSession().createCriteria(Remark.class,  UtilRepository.REMARK_ALIAS);
		criteria.add(Restrictions.eq("id", id)).add(UtilRepository.isActiveRestricition());
		List<RemarkBean> remarkBeans = UtilRepository.transferToRemarkBean(criteria).list();
		if(remarkBeans.isEmpty()) return null;
		return remarkBeans.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<RemarkBean> getAllRemarks() {
		Criteria criteria = getSession().createCriteria(Remark.class, UtilRepository.REMARK_ALIAS)
				.add(UtilRepository.isActiveRestricition());
		return UtilRepository.transferToRemarkBean(criteria).list();
	}
	
	public RemarkBean updateIsActive(long id, short isActive) {
		Remark remark= getSession().byId(Remark.class).load(id);
		if(remark != null) {
			remark.setIsActive(isActive);
			getSession().update(remark);
			return getRemark(id);
		}
		return null;
	}
}
