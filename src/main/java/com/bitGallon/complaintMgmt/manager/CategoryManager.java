package com.bitGallon.complaintMgmt.manager;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.bean.CategoryBean;
import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.repository.CategoryRepository;

@Repository
@Transactional
public class CategoryManager {
	@Autowired
	private CategoryRepository repository;

	public Long saveCategory(Category category) throws Exception {
		return repository.saveCategory(category);
	}
	
	public CategoryBean getCategory(int id) {
		return repository.getCategory(id);
	}
	
	public List<CategoryBean> getAllCateogories(){
		return repository.getAllCategory();
	}

	public CategoryBean updateIsActive(long id, short isActive) {
		return repository.updateIsActive(id, isActive);
	}
}
