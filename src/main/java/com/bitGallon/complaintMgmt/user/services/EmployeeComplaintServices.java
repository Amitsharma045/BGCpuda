package com.bitGallon.complaintMgmt.user.services;

import java.util.ArrayList;
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

import com.bitGallon.complaintMgmt.bean.CategoryBean;
import com.bitGallon.complaintMgmt.bean.ComplaintRegistrationBean;
import com.bitGallon.complaintMgmt.bean.MinCategorySubCategoryBean;
import com.bitGallon.complaintMgmt.bean.SubCategoryBean;
import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.json.JsonResponse;
import com.bitGallon.complaintMgmt.manager.AreaManager;
import com.bitGallon.complaintMgmt.manager.AttachmentDetailManager;
import com.bitGallon.complaintMgmt.manager.ComplaintManager;
import com.bitGallon.complaintMgmt.manager.IssueTypeManager;
import com.bitGallon.complaintMgmt.manager.PushNotificationManager;
import com.bitGallon.complaintMgmt.manager.StatusManager;
import com.bitGallon.complaintMgmt.notification.PushNotificationUtil;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.repository.UtilRepository;
import com.bitGallon.complaintMgmt.rest.RestResource;
import com.bitGallon.complaintMgmt.smsapi.sendSMS;
import com.bitGallon.complaintMgmt.util.PushNotificationMessageUtil;
import com.bitGallon.complaintMgmt.util.SmsMessagesUtil;

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
	
	@Autowired
	private  PushNotificationManager pushNotificationManager;
	
	private JsonResponse jsonResponse;
	
	@RequestMapping(value = "/v1.0/getAllComplaints/", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getAllComplaints(Pageable complaintId,
			@RequestParam(name= "startDate", required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date startDate,
		@RequestParam(name= "endDate", required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date endDate,
		@RequestParam(name="subCategoryId", required = false) Long subCategoryId,
		@RequestParam(name="prevComplaintId", required = false) Long prevComplaintId,
		@RequestParam(name="statusId", required = false) List<Long> statusId) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			List<ComplaintRegistration> complaintList = manager.getAllComplaintsForEmployee(complaintId, getUserId() , startDate , endDate, subCategoryId, prevComplaintId, statusId);
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
			ComplaintRegistrationBean complaintRegistrationBean = UtilRepository.createComplaintRepoBean(complaintRegistration, null);
			pushNotificationManager.sendResolvedComplaintNotifications(complaintRegistration);
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
	
	@RequestMapping(value = "/v1.0/updateComplaint/", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> updateComplaint(@RequestParam(name="complaintId") String complaintId, @RequestParam(name="subStatus") String subStatus,
			@RequestParam(name="comment") String comment) throws Exception {
		jsonResponse = new JsonResponse();
		try {
			ComplaintRegistration complaintRegistration = manager.updateComplaint(complaintId, getUserId(), subStatus, comment);
			pushNotificationManager.sendManualEscalationComplaintNotifications(complaintRegistration);
			ComplaintRegistrationBean complaintRegistrationBean = UtilRepository.createComplaintRepoBean(complaintRegistration, null);
			jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
			jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_SAVED);
			jsonResponse.setComplaintRegistrationBean(complaintRegistrationBean);
			log(clazz, ConstantProperty.INVALID_FILE_ERROR, ConstantProperty.LOG_DEBUG);
		} catch(Exception ex) {
			System.out.println(ex);
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
		}
		return sendResponse(jsonResponse);
	}
	
	 @RequestMapping(value={"/v1.0/getAssignedCategorySubCateogry"}, produces={"application/json"}, method={RequestMethod.GET})
	    @ResponseBody
	    public HashMap<String, Object> getAssignedCategoriesWithSubcategory() throws Exception {
	        this.jsonResponse = new JsonResponse();
	        try {
	            List categorySubCategoryBeanList = manager.getAssignedCategoriesSubCategory(getUserId());
	            jsonResponse.setStatusCode("200");
	            jsonResponse.setMessage("Information saved successfully");
	            jsonResponse.setCategorySubCateogryList(categorySubCategoryBeanList);
	        }
	        catch (Exception ex) {
	            jsonResponse.setStatusCode("501");
	            jsonResponse.setMessage("Internal Server Error");
	            log(this.clazz, ex.getMessage(), "ERROR");
	        }
	        return this.sendResponse(this.jsonResponse);
	    }

	    @RequestMapping(value={"/v1.0/getAssignedCategories"}, produces={"application/json"}, method={RequestMethod.GET})
	    @ResponseBody
	    public HashMap<String, Object> getAssignedCategories() throws Exception {
	        this.jsonResponse = new JsonResponse();
	        try {
	            List<Category> categoryList = manager.getAssignedCategories(getUserId());
	            jsonResponse.setStatusCode("200");
	            jsonResponse.setMessage("Information saved successfully");
	            jsonResponse.setCategoryBeanList(convertCategoryBean(categoryList));
	        }
	        catch (Exception ex) {
	            jsonResponse.setStatusCode("501");
	            jsonResponse.setMessage("Internal Server Error");
	            log(clazz, ex.getMessage(), "ERROR");
	        }
	        return sendResponse(jsonResponse);
	    }
	    
	    public List<CategoryBean> convertCategoryBean(List<Category> categoryList) {
	        ArrayList<CategoryBean> categoryBeanList = null;
	        if (categoryList != null && categoryList.size() > 0) {
	            categoryBeanList = new ArrayList<CategoryBean>();
	            for (Category category : categoryList) {
	                CategoryBean categoryBean = new CategoryBean();
	                categoryBean.setId(category.getId());
	                categoryBean.setName(category.getName());
	                categoryBeanList.add(categoryBean);
	            }
	        }
	        return categoryBeanList;
	    }
}
