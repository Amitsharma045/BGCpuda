package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bitGallon.complaintMgmt.entity.Category;

@Repository
@Transactional
public class CategoryRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Long saveCategory(Category category) throws Exception {
		return (Long) getSession().save(category);
	}

	public Category getCategory(long id) {
		List<Category> category=  getSession()
				.createQuery("FROM Category c WHERE c.id =:id AND " + UtilRepository.getIsActiveQuery("c"))
				.setParameter("id", id).list();
		if (category.size() == 1) return category.get(0);
		else return null;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategory() {
		return (List<Category>) getSession()
				.createQuery("FROM Category c WHERE " + UtilRepository.getIsActiveQuery("c")).list();
	}
}
