package com.bitGallon.complaintMgmt.user.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bitGallon.complaintMgmt.bean.ComplaintRegistrationBean;
import com.bitGallon.complaintMgmt.entity.AttachmentDetail;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.entity.User;
import com.bitGallon.complaintMgmt.json.JsonResponse;
import com.bitGallon.complaintMgmt.manager.AreaManager;
import com.bitGallon.complaintMgmt.manager.AttachmentDetailManager;
import com.bitGallon.complaintMgmt.manager.ComplaintManager;
import com.bitGallon.complaintMgmt.manager.IssueTypeManager;
import com.bitGallon.complaintMgmt.manager.PushNotificationManager;
import com.bitGallon.complaintMgmt.manager.StatusManager;
import com.bitGallon.complaintMgmt.notification.PushNotificationUtil;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.rest.RestResource;
import com.bitGallon.complaintMgmt.smsapi.sendSMS;
import com.bitGallon.complaintMgmt.util.CommonUtil;
import com.bitGallon.complaintMgmt.util.PushNotificationMessageUtil;
import com.bitGallon.complaintMgmt.util.SmsMessagesUtil;


@Controller
@RequestMapping(value = "/bitGallon/api/user/complaint")
public class UserComplaintServices extends RestResource {
	
	private Class clazz = UserComplaintServices.class;

	
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
	
	@Autowired
	private  PushNotificationManager pushNotificationManager;
	
	
	private JsonResponse jsonResponse;


	@RequestMapping(value = "/v1.0/getAllComplaints/", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getAllComplaints(Pageable page,
			@RequestParam(name= "startDate", required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date startDate,
		@RequestParam(name= "endDate", required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date endDate,
		@RequestParam(name="categoryId", required = false) Long categoryId,
		@RequestParam(name="statusId", required = false) List<Long> statusId) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			List<ComplaintRegistration> complaintList = manager.getAllComplaintsForUser(page, getUserId() , startDate , endDate, categoryId, statusId);
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
	public HashMap<String,Object> getComplaints(Pageable page,
		@RequestParam(name="complaintId") String complaintId) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			ComplaintRegistrationBean complaintRegistrationBean = manager.getComplaintByComplaintNumber(complaintId, getUserId());
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/v1.0/saveComplaint", produces={"application/json"},
			method = RequestMethod.POST)
	@ResponseBody  
	public HashMap<String,Object> saveComplaint(HttpServletRequest request,@RequestParam("uploadingFiles")  MultipartFile[] uploadedFiles) throws Exception {
		
		String issueTitle = request.getParameter(ConstantProperty.ISSUE_TITLE);
		String issueType = request.getParameter(ConstantProperty.ISSUE_TYPE);
		String areaName = request.getParameter(ConstantProperty.AREA_NAME);
		String complaintLat = request.getParameter(ConstantProperty.COMPLAINT_LATITUDE);
		String complaintLng = request.getParameter(ConstantProperty.COMPLAINT_LOGITUDE);
		String landmark = request.getParameter(ConstantProperty.LANDMARK);
		
		jsonResponse = new JsonResponse();
		try {
			AttachmentDetail [] attachmentDetails = new AttachmentDetail[uploadedFiles.length];
			if(attachmentDetails.length>0 && !validateFiles(uploadedFiles,attachmentDetails,jsonResponse)) {
				log(clazz, ConstantProperty.INVALID_FILE_ERROR, ConstantProperty.LOG_DEBUG);
				return sendResponse(jsonResponse);
			}
			User appUser = new User();
			appUser.setId(getUserId());
			ComplaintRegistration complaintRegistration = new ComplaintRegistration();
			complaintRegistration.setUser(appUser);
			complaintRegistration.setIssueTitle(issueTitle);
			complaintRegistration.setIssueType(issueTypeManager.getIssueType(issueType));
			complaintRegistration.setArea(areaManager.getArea(areaName));
			complaintRegistration.setComplaintLat(Double.valueOf(complaintLat));
			complaintRegistration.setComplaintLng(Double.valueOf(complaintLng));
			complaintRegistration.setLandMark(landmark);
			complaintRegistration.setIsActive((short)1);
			complaintRegistration.setComplaintLevel((short)0);
			complaintRegistration.setStatus(statusManager.getStatus(ConstantProperty.STATUS_IN_PROGRESS));
			complaintRegistration.setSubStatus(statusManager.getSubStatus(ConstantProperty.SUB_STATUS_IN_PROGRESS));
			
			ComplaintRegistration savedComplaint=manager.saveComplaintRegistration(complaintRegistration);
			if(savedComplaint!=null && savedComplaint.getId()!=0) {
				if(attachmentDetails.length>0) {
					attachmentDetails = attachmentManager.saveAttachments(savedComplaint,attachmentDetails,uploadedFiles);
				}
				pushNotificationManager.sendNewComplaintNotifications(complaintRegistration);
				jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
				jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_SAVED);
				log(clazz, ConstantProperty.SUCCESSFUL_SAVED, ConstantProperty.LOG_DEBUG);
				return sendResponse(jsonResponse); 
			}else {
				jsonResponse.setStatusCode(ConstantProperty.METHOD_FAILURE);
				jsonResponse.setMessage(ConstantProperty.FAILURE_NOT_SAVED);
				log(clazz, ConstantProperty.FAILURE_NOT_SAVED, ConstantProperty.LOG_DEBUG);
			}
			
		} catch (Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
			return sendResponse(jsonResponse);
		}
		
		return sendResponse(jsonResponse);
	}
	
	private boolean validateFiles(MultipartFile[] uploadedFiles, AttachmentDetail[] attachmentDetails, 
			JsonResponse jsonResponse) throws Exception
	{
		boolean hasValidate=true;
		long totalFileSize=0;
		if(uploadedFiles.length>0) {
			for (int i=0;i<uploadedFiles.length;i++) {
				if(!validateFileExt(uploadedFiles[i],attachmentDetails[i],jsonResponse)) {
					hasValidate=false;
					break;
				}
				else if(totalFileSize<=ConstantProperty.MAX_FILES_LIMIT){
					totalFileSize+=uploadedFiles[i].getSize();
				}
				else {
					hasValidate=false;
					jsonResponse.setStatusCode(ConstantProperty.FILE_SIZE_LIMIT); 
					jsonResponse.setMessage(ConstantProperty.FILE_SIZE_ERROR);
					break;
				}
			}
		}
		else hasValidate=false;
		return hasValidate;
	}
	
	private boolean validateFileExt(MultipartFile uploadedFile, AttachmentDetail attachmentDetails, 
			JsonResponse jsonResponse) throws Exception
	{
		return true;
	}
	
	/*
	 * private boolean validateFileExt(MultipartFile uploadedFile, AttachmentDetail
	 * attachmentDetails, JsonResponse jsonResponse) throws Exception {
	 * if(CommonUtil.getMatchingStrings(CommonUtil.listOfAcceptedFiles(),
	 * uploadedFile.getContentType())) { return true; } else {
	 * jsonResponse.setStatusCode(ConstantProperty.FILE_SIZE_LIMIT);
	 * jsonResponse.setMessage(ConstantProperty.FILE_SIZE_ERROR); return false; } }
	 */
}
