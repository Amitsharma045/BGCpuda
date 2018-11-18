package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.SubCategoryBean;
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
	public SubCategoryBean getSubCategory(long id) {
		Criteria criteria = getSession().createCriteria(SubCategory.class, UtilRepository.SUB_CATEGORY_ALIAS);
		criteria.add(Restrictions.eq("id", id)).add(UtilRepository.isActiveRestricition());
		List<SubCategoryBean> subCategoryBeans = UtilRepository.transferToSubCategoryBean(criteria).list();
		if (subCategoryBeans.isEmpty())	return null;
		return subCategoryBeans.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<SubCategoryBean> getAllSubCategories() {
		Criteria criteria = getSession().createCriteria(SubCategory.class, UtilRepository.SUB_CATEGORY_ALIAS)
				.add(UtilRepository.isActiveRestricition());
		return UtilRepository.transferToSubCategoryBean(criteria).list();
	}

	@SuppressWarnings("unchecked")
	public List<SubCategoryBean> getAllSubCategories(long categoryId) {
		Criteria criteria = getSession().createCriteria(SubCategory.class, UtilRepository.SUB_CATEGORY_ALIAS)
				.add(Restrictions.eq("category.id", categoryId)).add(UtilRepository.isActiveRestricition());
		return UtilRepository.transferToSubCategoryBean(criteria).list();
	}
	
	public SubCategoryBean updateIsActive(long id, short isActive) {
		SubCategory subCategory= getSession().byId(SubCategory.class).load(id);
		if(subCategory != null) {
			subCategory.setIsActive(isActive);
			getSession().update(subCategory);
			return getSubCategory(id);
		}
		return null;
	}
}
