package com.bitGallon.complaintMgmt.rest;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.bitGallon.complaintMgmt.json.JsonResponse;
import com.bitGallon.complaintMgmt.property.ConstantProperty;


/**
 * @author rpsingh
 *
 */
public abstract class RestResource {

	@Autowired
	protected HttpServletRequest  request_;
	
	protected LinkedHashMap<String, Object> sendResponse(JsonResponse jsonResponse) throws Exception {
		LinkedHashMap<String, Object> json = new LinkedHashMap<String, Object>();
		json.put(ConstantProperty.STATUS_CODE, jsonResponse.getStatusCode());
		json.put(ConstantProperty.MESSAGE, jsonResponse.getMessage());
		if(jsonResponse.getAccessToken() != null)
			json.put(ConstantProperty.ACCESS_TOKEN, jsonResponse.getAccessToken());
		return json;
	}
	
	protected boolean isUserAdmin() {
		String role = request_.getAttribute("role").toString();
		if("Admin".equals(role)) return true;
		return false;
	}
	
	public Long getUserId() {
		return Long.parseLong(request_.getAttribute("appUserId").toString());
	}
	
	protected void log(Class clazz, String message, String tag) {
		Logger logger = LoggerFactory.getLogger(clazz);
		if(ConstantProperty.LOG_INFO.equals(tag)) {
			logger.info(message);
		} else if(ConstantProperty.LOG_WARNING.equals(tag)) {
			logger.warn(message);
		} else if(ConstantProperty.LOG_ERROR.equals(tag)) {
			logger.error(message);
		} else if(ConstantProperty.LOG_DEBUG.equals(tag)) {
			logger.debug(message);
		} else {
			logger.trace(message);
		}
	}
}
