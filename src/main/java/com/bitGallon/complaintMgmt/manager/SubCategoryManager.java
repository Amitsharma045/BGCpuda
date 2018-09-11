package com.bitGallon.complaintMgmt.manager;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.SubCategory;
import com.bitGallon.complaintMgmt.repository.SubCategoryRepository;

@Repository
@Transactional
public class SubCategoryManager {
	@Autowired
	private SubCategoryRepository repository;

	public Long saveSubCategory(SubCategory subCategory) throws Exception {
		return repository.saveSubCategory(subCategory);
	}
	
	public SubCategory getSubCategory(long id) {
		return repository.getSubCategory(id);
	}
	
	public List<SubCategory> getAllSubCategories(){
		return repository.getAllSubCategories();
	}
}
