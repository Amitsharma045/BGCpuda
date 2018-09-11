package com.bitGallon.complaintMgmt.services;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.entity.IssueType;
import com.bitGallon.complaintMgmt.manager.IssueTypeManager;
import com.bitGallon.complaintMgmt.repository.IssueTypeRepository;

@Controller
@RequestMapping(value = "/bitGallon/api/issueType")
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
	public IssueType getIssueTpe(@RequestParam("id") int id) {
		return manager.getIssueType(id);
	}
	
	@RequestMapping(value = "/v1.0/getIssueTypes", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public List<IssueType> getIssueTypes() {
		return manager.getAllIssueTypes();
	}
}
