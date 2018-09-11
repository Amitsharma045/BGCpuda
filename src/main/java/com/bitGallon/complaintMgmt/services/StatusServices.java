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

import com.bitGallon.complaintMgmt.entity.ComplaintStatus;
import com.bitGallon.complaintMgmt.manager.StatusManager;
import com.bitGallon.complaintMgmt.repository.StatusRepository;

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
	public ComplaintStatus getStatus(@RequestParam("id") int id) {
		return manager.getStatus(id);
	}
	
	@RequestMapping(value = "/v1.0/getStatues", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public List<ComplaintStatus> getAllStatues() {
		return manager.getAllStatus();
	}
}
