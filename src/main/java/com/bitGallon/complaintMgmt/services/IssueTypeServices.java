package com.bitGallon.complaintMgmt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.bean.CategoryBean;
import com.bitGallon.complaintMgmt.bean.IssueTypeBean;
import com.bitGallon.complaintMgmt.entity.IssueType;
import com.bitGallon.complaintMgmt.manager.IssueTypeManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/bitGallon/api/issueType")
@Api(value="Various operations on Issue Types")
public class IssueTypeServices {
	@Autowired
	private IssueTypeManager manager;

	@RequestMapping(value = "/v1.0/saveIssueType", produces={"application/json"},
			method = RequestMethod.POST)
	@ResponseBody
	public Long saveIssueType(IssueType issueType) throws Exception {
		return manager.saveIssueType(issueType);
	}
	
	@RequestMapping(value = "/v1.0/getIssueType", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public IssueTypeBean getIssueTpe(@RequestParam("id") int id) {
		return manager.getIssueType(id);
	}
	
	@ApiOperation(value = "if subCatId is provided, This method will return all the Issue types under given subCategoryId\r\n" + 
			"	 else will return This method will return all the Issue types ")
	@RequestMapping(value = "/v1.0/getIssueTypes", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public List<IssueTypeBean> getIssueTypes(@RequestParam(value = "subCatId" , required = false)  Long subCatId) {
		return manager.getAllIssueTypes(subCatId);
	}
	
	@RequestMapping(value = "/v1.0/updateIsActive", produces = { "application/json" }, method = RequestMethod.PUT)
	@ResponseBody
	public IssueTypeBean updateIsActive(@RequestParam("id") long id, @RequestParam("isActive") short isActive) {
		return manager.updateIsActive(id, isActive);
	}
}
