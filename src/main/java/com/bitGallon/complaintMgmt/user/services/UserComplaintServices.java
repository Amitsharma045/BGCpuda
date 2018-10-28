package com.bitGallon.complaintMgmt.user.services;

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
import com.bitGallon.complaintMgmt.manager.CategoryManager;
import com.bitGallon.complaintMgmt.manager.ComplaintManager;

@Controller
@RequestMapping(value = "/bitGallon/user/complaint")
public class UserComplaintServices {
	@Autowired
	private ComplaintManager manager;

	/*@RequestMapping(value = "/v1.0/saveCategory", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public Long saveCategory(Category category) throws Exception {
		return manager.saveCategory(category);
	}

	@RequestMapping(value = "/v1.0/getCategory", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public CategoryBean getCategory(@RequestParam("id") int id) {
		return manager.getCategory(id);
	}
*/
	@RequestMapping(value = "/v1.0/getComplaints", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public List<ComplaintRegistration> getAllCategories(Pageable page, @RequestParam("userId") long userId) {
		return manager.getAllComplaintsForUser(page, userId);
	}

	/*@RequestMapping(value = "/v1.0/updateIsActive", produces = { "application/json" }, method = RequestMethod.PUT)
	@ResponseBody
	public CategoryBean updateIsActive(@RequestParam("id") long id, @RequestParam("isActive") short isActive) {
		return manager.updateIsActive(id, isActive);
	}
*/
}
