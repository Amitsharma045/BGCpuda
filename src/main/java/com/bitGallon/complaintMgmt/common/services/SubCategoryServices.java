package com.bitGallon.complaintMgmt.common.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.bean.SubCategoryBean;
import com.bitGallon.complaintMgmt.entity.SubCategory;
import com.bitGallon.complaintMgmt.json.JsonResponse;
import com.bitGallon.complaintMgmt.manager.SubCategoryManager;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.rest.RestResource;

@Controller
@RequestMapping(value = "/bitGallon/api/subCategories")
public class SubCategoryServices extends RestResource {
	
	private Class clazz = SubCategoryServices.class;
	
	private JsonResponse jsonResponse;
	
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
	public HashMap<String,Object> getSubCategory(@RequestParam("id") long id) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			SubCategoryBean subCategoryBean = manager.getSubCategory(id);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_PROCESSED);
			jsonResponse.setSubCategoryBean(subCategoryBean);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	
	}
	
	@RequestMapping(value = "/v1.0/getSubCategories", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getAllSubCategories(@RequestParam(value = "categoryId" , required = false) Long categoryId) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			List<SubCategoryBean> subCategoryBeanList = manager.getAllSubCategories(categoryId);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_PROCESSED);
			jsonResponse.setSubCategoryBeanList(subCategoryBeanList);;
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}
	
	@RequestMapping(value = "/v1.0/updateIsActive", produces = { "application/json" }, method = RequestMethod.PUT)
	@ResponseBody
	public SubCategoryBean updateIsActive(@RequestParam("id") long id, @RequestParam("isActive") short isActive) {
		return manager.updateIsActive(id, isActive);
	}
}
