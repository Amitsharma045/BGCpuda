package com.bitGallon.complaintMgmt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.bean.SubCategoryBean;
import com.bitGallon.complaintMgmt.entity.SubCategory;
import com.bitGallon.complaintMgmt.manager.SubCategoryManager;

@Controller
@RequestMapping(value = "/bitGallon/api/subCategories")
public class SubCategoryServices {
	@Autowired
	private SubCategoryManager manager;

	@RequestMapping(value = "/v1.0/saveSubCategory", produces={"application/json"},
			method = RequestMethod.POST)
	@ResponseBody
	public Long saveSubCategory(SubCategory subCategory) throws Exception {
		return manager.saveSubCategory(subCategory);
	}
	@RequestMapping(value = "/v1.0/getSubCategory", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public SubCategoryBean getSubCategory(@RequestParam("id") long id) {
		return manager.getSubCategory(id);
	}
	
	@RequestMapping(value = "/v1.0/getSubCategories", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public List<SubCategoryBean> getAllSubCategories(@RequestParam(value = "categoryId" , required = false) Long categoryId) {
		return manager.getAllSubCategories(categoryId);
	}
}
