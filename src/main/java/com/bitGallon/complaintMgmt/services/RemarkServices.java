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

import com.bitGallon.complaintMgmt.entity.IssueType;
import com.bitGallon.complaintMgmt.entity.Remark;
import com.bitGallon.complaintMgmt.manager.RemarkManager;
import com.bitGallon.complaintMgmt.repository.RemarkRepository;

@Controller
@RequestMapping(value = "/bitGallon/api/remarks")
public class RemarkServices {
	@Autowired
	private RemarkManager manager;
	@RequestMapping(value = "/v1.0/saveSubCategory", produces={"application/json"},
			method = RequestMethod.POST)
	@ResponseBody
	public Long saveSubCategory(Remark remark) throws Exception {
		return manager.saveRemark(remark);
	}
	
	@RequestMapping(value = "/v1.0/getRemark", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public Remark getRemakr(@RequestParam("id") int id) {
		return manager.getRemark(id);
	}
	
	@RequestMapping(value = "/v1.0/getRemarks", produces={"application/json"},
			method = RequestMethod.GET)
	@ResponseBody
	public List<Remark> getAllRemarks() {
		return manager.getAllRemarks();
	}
}
