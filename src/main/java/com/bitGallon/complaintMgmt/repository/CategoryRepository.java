package com.bitGallon.complaintMgmt.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.CategoryBean;
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

	@SuppressWarnings("unchecked")
	public CategoryBean getCategory(long id) {
		Criteria criteria = getSession().createCriteria(Category.class, UtilRepository.CATEGORY_ALIAS);
		criteria.add(Restrictions.eq("id", id)).add(UtilRepository.isActiveRestricition());
		List<CategoryBean> categoryBeans = UtilRepository.transferToCategoryBean(criteria).list();
		if(categoryBeans.isEmpty()) return null;
		return categoryBeans.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<CategoryBean> getAllCategory() {
		Criteria criteria = getSession().createCriteria(Category.class, UtilRepository.CATEGORY_ALIAS);
		criteria.add(UtilRepository.isActiveRestricition()); 
		return UtilRepository.transferToCategoryBean(criteria).list();
	}

	public CategoryBean updateIsActive(long id, short isActive) {
		Category category = getSession().byId(Category.class).load(id);
		if(category != null) {
			category.setIsActive((short) (isActive == 0 ? 0 : 1) );
			getSession().update(category);
			return getCategory(id);
		}
		return null;
	}
}
