package com.bitGallon.complaintMgmt.user.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.bean.ComplaintRegistrationBean;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.json.JsonResponse;
import com.bitGallon.complaintMgmt.manager.AreaManager;
import com.bitGallon.complaintMgmt.manager.AttachmentDetailManager;
import com.bitGallon.complaintMgmt.manager.ComplaintManager;
import com.bitGallon.complaintMgmt.manager.IssueTypeManager;
import com.bitGallon.complaintMgmt.manager.StatusManager;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.rest.RestResource;

@Controller
@RequestMapping(value = "/bitGallon/api/employee/complaint")
public class EmployeeComplaintServices  extends RestResource {
	
	private Class clazz = EmployeeComplaintServices.class;
	
	@Autowired
	private ComplaintManager manager;
	
	@Autowired
	private  IssueTypeManager issueTypeManager;
	
	@Autowired
	private  AreaManager areaManager;
	
	@Autowired
	private  AttachmentDetailManager attachmentManager;
	
	@Autowired
	private  StatusManager statusManager;
	
	
	private JsonResponse jsonResponse;
	
	@RequestMapping(value = "/v1.0/getAllComplaints/", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getAllComplaints(Pageable complaintId,
			@RequestParam(name= "startDate", required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date startDate,
		@RequestParam(name= "endDate", required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date endDate,
		@RequestParam(name="categoryId", required = false) Long subCategoryId) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			List<ComplaintRegistration> complaintList = manager.getAllComplaintsForEmployee(complaintId, getUserId() , startDate , endDate, subCategoryId);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_SAVED);
			jsonResponse.setComplaintList(complaintList);
			log(clazz, ConstantProperty.INVALID_FILE_ERROR, ConstantProperty.LOG_DEBUG);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}
	
	@RequestMapping(value = "/v1.0/getComplaints/", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getComplaints(@RequestParam(name="complaintId") String complaintId) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			ComplaintRegistrationBean complaintRegistrationBean = manager.getComplaintByComplaintNumberForEmployee(complaintId, getUserId());
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_SAVED);
			jsonResponse.setComplaintRegistrationBean(complaintRegistrationBean);
			log(clazz, ConstantProperty.INVALID_FILE_ERROR, ConstantProperty.LOG_DEBUG);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
		
	}

	@RequestMapping(value = "/v1.0/resolveComplaint/", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> resolveComplaint(@RequestParam(name="complaintId") String complaintId, @RequestParam(name="subStatus") String subStatus,
			@RequestParam(name="comment") String comment) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			ComplaintRegistration complaintRegistration = manager.resolveComplaint(complaintId, getUserId(), subStatus, comment);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_SAVED);
			jsonResponse.setComplaintRegistration(complaintRegistration);
			log(clazz, ConstantProperty.INVALID_FILE_ERROR, ConstantProperty.LOG_DEBUG);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}
	
	@RequestMapping(value = "/v1.0/updateComplaint/", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> updateComplaint(@RequestParam(name="complaintId") String complaintId, @RequestParam(name="subStatus") String subStatus,
			@RequestParam(name="comment") String comment) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			ComplaintRegistration complaintRegistration = manager.updateComplaint(complaintId, getUserId(), subStatus, comment);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_SAVED);
			jsonResponse.setComplaintRegistration(complaintRegistration);
			log(clazz, ConstantProperty.INVALID_FILE_ERROR, ConstantProperty.LOG_DEBUG);
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}
	
}
