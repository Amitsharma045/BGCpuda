package com.bitGallon.complaintMgmt.common.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.bean.ComplaintStatusBean;
import com.bitGallon.complaintMgmt.bean.ParentComplaintStatusBean;
import com.bitGallon.complaintMgmt.entity.ComplaintStatus;
import com.bitGallon.complaintMgmt.json.JsonResponse;
import com.bitGallon.complaintMgmt.manager.StatusManager;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.rest.RestResource;

@Controller
@RequestMapping(value = "/bitGallon/api/status")
public class StatusServices  extends RestResource  {
	private Class clazz = StatusServices.class;
	
	private JsonResponse jsonResponse;
	
	@Autowired
	private StatusManager manager;

	@RequestMapping(value = "/v1.0/saveStatus", produces={"application/json"},
			method = RequestMethod.POST)
	@ResponseBody 
	public Long saveStatus(ComplaintStatus status) throws Exception {
		return manager.saveStatus(status);
	}
	
	@RequestMapping(value = "/v1.0/getStatus", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getStatus(@RequestParam("id") long id) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			ComplaintStatusBean complaintStatusBean = manager.getStatus(id);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_PROCESSED);
			jsonResponse.setComplaintStatusBean(complaintStatusBean);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}
	
	@RequestMapping(value = "/v1.0/getStatues", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getAllStatues(@RequestParam("parentId") long parentId) throws Exception {

		jsonResponse = new JsonResponse();
		try {
			List<ComplaintStatusBean> complaintStatusBeanList = manager.getAllStatus(parentId);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_PROCESSED);
			jsonResponse.setComplaintStatusBeanList(complaintStatusBeanList);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}
	
	@RequestMapping(value = "/v1.0/getParentStatues", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public LinkedHashMap<String, Object> getParentStatus() throws Exception {
		jsonResponse = new JsonResponse();
		try {
		List<ParentComplaintStatusBean> parentComplaintStatusBeans = manager.getParentStatus();
		jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
		jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_PROCESSED);
		jsonResponse.setStatusBeans(parentComplaintStatusBeans);
	} catch(Exception ex) {
		jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
		jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
		log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
	}
	return sendResponse(jsonResponse);
	}
	@RequestMapping(value = "/v1.0/updateIsActive", produces = { "application/json" }, method = RequestMethod.PUT)
	@ResponseBody
	public ComplaintStatusBean updateIsActive(@RequestParam("id") long id, @RequestParam("isActive") short isActive) {
		return manager.updateIsActive(id, isActive);
	}
}
