package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	public Remark getRemark(long id) {
		List<Remark> remarks = getSession().createQuery("FROM Remark r WHERE r.id =:id " + UtilRepository.getIsActiveQuery("r")).
				setParameter("id", id).list();
		if (remarks.size() == 1) return remarks.get(0);
		else return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Remark> getAllRemarks() {
		return (List<Remark>)getSession().createQuery("FROM Remark r WHERE " + UtilRepository.getIsActiveQuery("r")).list();
	}
}
