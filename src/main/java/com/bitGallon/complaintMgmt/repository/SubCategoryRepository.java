package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.SubCategory;

@Repository
@Transactional
public class SubCategoryRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public Long saveSubCategory(SubCategory subCategory) throws Exception {
		Long id = (Long) getSession().save(subCategory);
		return id;
	}
	@SuppressWarnings("unchecked")
	public SubCategory getSubCategory(long id) {
		List<SubCategory> subCategories =  getSession()
				.createQuery("FROM SubCategory sC WHERE sC.id =:id AND " + UtilRepository.getIsActiveQuery("sC"))
				.setParameter("id", id).list();
		if (subCategories.size() == 1) return subCategories.get(0);
		else return null;
	}

	@SuppressWarnings("unchecked")
	public List<SubCategory> getAllSubCategories() {
		return (List<SubCategory>) getSession()
				.createQuery("FROM SubCategory sC WHERE " + UtilRepository.getIsActiveQuery("sC")).list();
	}
}
