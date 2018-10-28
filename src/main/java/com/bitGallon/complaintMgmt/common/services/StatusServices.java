package com.bitGallon.complaintMgmt.common.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.bean.ComplaintStatusBean;
import com.bitGallon.complaintMgmt.bean.RemarkBean;
import com.bitGallon.complaintMgmt.entity.ComplaintStatus;
import com.bitGallon.complaintMgmt.manager.StatusManager;

@Controller
@RequestMapping(value = "/bitGallon/api/status")
public class StatusServices {
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
	public ComplaintStatusBean getStatus(@RequestParam("id") long id) {
		return manager.getStatus(id);
	}
	
	@RequestMapping(value = "/v1.0/getStatues", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public List<ComplaintStatusBean> getAllStatues(@RequestParam("parentId") long parentId) {
		return manager.getAllStatus(parentId);
	}
	
	@RequestMapping(value = "/v1.0/updateIsActive", produces = { "application/json" }, method = RequestMethod.PUT)
	@ResponseBody
	public ComplaintStatusBean updateIsActive(@RequestParam("id") long id, @RequestParam("isActive") short isActive) {
		return manager.updateIsActive(id, isActive);
	}
}
