package com.bitGallon.complaintMgmt.common.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.bean.CategoryBean;
import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.json.JsonResponse;
import com.bitGallon.complaintMgmt.manager.CategoryManager;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.rest.RestResource;
import com.bitGallon.complaintMgmt.user.services.UserComplaintServices;

@Controller
@RequestMapping(value = "/bitGallon/user/category")
public class CategoryServices extends RestResource {
	
	private Class clazz = CategoryServices.class;

	@Autowired
	private CategoryManager manager;
	
	private JsonResponse jsonResponse;


	@RequestMapping(value = "/v1.0/saveCategory", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> saveCategory(Category category) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			Long id = manager.saveCategory(category);
			if(id != null){
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_SAVED);
			}
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}

	@RequestMapping(value = "/v1.0/getCategory", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getCategory(@RequestParam("id") int id) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			CategoryBean categoryBean = manager.getCategory(id);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_PROCESSED);
			jsonResponse.setCategoryBean(categoryBean);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}

	@RequestMapping(value = "/v1.0/getCategories", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getAllCategories() throws Exception {
		jsonResponse = new JsonResponse();
		try {
			List<CategoryBean> categoryBeanList = manager.getAllCateogories();
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_PROCESSED);
			jsonResponse.setCategoryBeanList(categoryBeanList);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}

	@RequestMapping(value = "/v1.0/updateIsActive", produces = { "application/json" }, method = RequestMethod.PUT)
	@ResponseBody
	public HashMap<String,Object> updateIsActive(@RequestParam("id") long id, @RequestParam("isActive") short isActive) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			CategoryBean categoryBean = manager.updateIsActive(id, isActive);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_PROCESSED);
			jsonResponse.setCategoryBean(categoryBean);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}

}
