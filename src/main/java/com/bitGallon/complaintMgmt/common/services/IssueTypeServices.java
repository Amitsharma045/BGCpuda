package com.bitGallon.complaintMgmt.common.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.bean.IssueTypeBean;
import com.bitGallon.complaintMgmt.entity.IssueType;
import com.bitGallon.complaintMgmt.json.JsonResponse;
import com.bitGallon.complaintMgmt.manager.IssueTypeManager;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.rest.RestResource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/bitGallon/api/issueType")
@Api(value="Various operations on Issue Types")
public class IssueTypeServices extends RestResource {

	private Class clazz = IssueTypeServices.class;

	
	@Autowired
	private IssueTypeManager manager;
	
	private JsonResponse jsonResponse;
	
	@RequestMapping(value = "/v1.0/saveIssueType", produces={"application/json"},
			method = RequestMethod.POST)
	@ResponseBody
	public Long saveIssueType(IssueType issueType) throws Exception {
		return manager.saveIssueType(issueType);
	}
	
	@RequestMapping(value = "/v1.0/getIssueType", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getIssueTpe(@RequestParam("id") int id) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			IssueTypeBean issueTypeBean = manager.getIssueType(id);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_PROCESSED);
			jsonResponse.setIssueTypeBean(issueTypeBean);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}
	
	@ApiOperation(value = "if subCatId is provided, This method will return all the Issue types under given subCategoryId\r\n" + 
			"	 else will return This method will return all the Issue types ")
	@RequestMapping(value = "/v1.0/getIssueTypes", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getIssueTypes(@RequestParam(value = "subCatId" , required = false)  Long subCatId) throws Exception {

		jsonResponse = new JsonResponse();
		try {
			List<IssueTypeBean> issueTypeBeanList =manager.getAllIssueTypes(subCatId);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_PROCESSED);
			jsonResponse.setIssueTypeBeanList(issueTypeBeanList);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}
	
	@RequestMapping(value = "/v1.0/updateIsActive", produces = { "application/json" }, method = RequestMethod.PUT)
	@ResponseBody
	public IssueTypeBean updateIsActive(@RequestParam("id") long id, @RequestParam("isActive") short isActive) {
		return manager.updateIsActive(id, isActive);
	}
}
