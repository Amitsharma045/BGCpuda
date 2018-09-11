package com.bitGallon.complaintMgmt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.manager.CategoryManager;

@Controller
@RequestMapping(value = "/bitGallon/api/category")
public class CategoryServices {
	@Autowired
	private CategoryManager manager;

	@RequestMapping(value = "/v1.0/saveCategory", produces={"application/json"},
			method = RequestMethod.POST)
	@ResponseBody
	public Long saveCategory(Category category) throws Exception {
		return manager.saveCategory(category);
	}
	
	@RequestMapping(value = "/v1.0/getCategory", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public Category getCategory(@RequestParam("id") int id) {
		return manager.getCategory(id);
	}
	
	@RequestMapping(value = "/v1.0/getCategories", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getAllCategories() {
		return manager.getAllCateogories();
	}
}
