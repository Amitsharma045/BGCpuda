package com.bitGallon.complaintMgmt.authentication;


import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.OtpTransectionDetail;
import com.bitGallon.complaintMgmt.entity.User;
import com.bitGallon.complaintMgmt.facebook.FBConnection;
import com.bitGallon.complaintMgmt.facebook.FBGraph;
import com.bitGallon.complaintMgmt.json.JsonResponse;
import com.bitGallon.complaintMgmt.manager.AuthenticationManager;
import com.bitGallon.complaintMgmt.manager.EmployeeManager;
import com.bitGallon.complaintMgmt.manager.JwtTokenManager;
import com.bitGallon.complaintMgmt.manager.OtpDetailManager;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.rest.JwtTokenFactory;
import com.bitGallon.complaintMgmt.rest.JwtUtil;
import com.bitGallon.complaintMgmt.rest.RestResource;
import com.bitGallon.complaintMgmt.smsapi.sendSMS;
import com.bitGallon.complaintMgmt.util.CommonUtil;
import com.bitGallon.complaintMgmt.util.SmsMessagesUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;




/**
 * @author rpsingh
 *
 */
@Controller
@RequestMapping(value = "/bgcpuda/authentication")
public class AppAuthenticationEndPoint extends RestResource {
	
	private Class clazz = AppAuthenticationEndPoint.class;
	
	@Autowired
	private  AuthenticationManager authenticationManager;
	
	@Autowired
	private  OtpDetailManager otpDetailManager;
	
	@Autowired
	private  JwtTokenManager jwtTokenManager;
	
	@Autowired
	private  EmployeeManager employeeManager;
	
	private JsonResponse jsonResponse;
	
	HashMap<String, String> smsStatus = null;

	@RequestMapping(value = "/user/v1.0/login", produces={"application/json"},
					method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> authenticateUser(HttpServletRequest request) throws Exception {
		String phoneNumber = request.getParameter(ConstantProperty.MOBILE_NUMBER);
		jsonResponse = new JsonResponse();
		String otp = CommonUtil.getRandomOtp();
		try {
			Long.valueOf(phoneNumber);
			OtpTransectionDetail otpTransectionDetails = getOtpDetail(phoneNumber, otp, null, ConstantProperty.USER);
			Long id = otpDetailManager.saveOtpDetails(otpTransectionDetails);
			if(id != null) {
				jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
				jsonResponse.setMessage(ConstantProperty.OTP_SENT);
				String messageBody = SmsMessagesUtil.getMessageForOtp("1234");
				smsStatus = sendSMS.sendSms(phoneNumber, messageBody);
				if(!smsStatus.get(ConstantProperty.STATUS_CODE).equals(ConstantProperty.OK_STATUS)) {
					jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
					jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
					log(clazz, "OTP NOT SENT", ConstantProperty.LOG_ERROR);

				}
			}
			else {
				jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
				jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
				log(clazz, "NOT ABLE TO GENERATE OTP", ConstantProperty.LOG_TRACE);

			}
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
			return sendResponse(jsonResponse);
		}
		return sendResponse(jsonResponse);
	}


	@RequestMapping(value = "/employee/v1.0/login", produces={"application/json"},
			method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> authenticateEmployee(HttpServletRequest request) throws Exception {
		String mobileNumber = request.getParameter(ConstantProperty.MOBILE_NUMBER);
		jsonResponse = new JsonResponse();
		String otp = CommonUtil.getRandomOtp();
		try {
			Long.valueOf(mobileNumber);
			Employee emp = employeeManager.getEmployee(mobileNumber);
			if(emp == null) {
				jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
				jsonResponse.setMessage(ConstantProperty.NOT_RESISTERED_EMPLOYEE);
				return sendResponse(jsonResponse);
			}
			OtpTransectionDetail otpTransectionDetails = getOtpDetail(mobileNumber, otp, null, ConstantProperty.EMPLOYEE);
			Long id = otpDetailManager.saveOtpDetails(otpTransectionDetails);
			if(id != null) {
				jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
				jsonResponse.setMessage(ConstantProperty.OTP_SENT);
				String messageBody = SmsMessagesUtil.getMessageForOtp("1234");
				smsStatus = sendSMS.sendSms(mobileNumber, messageBody);
				if(!smsStatus.get(ConstantProperty.STATUS_CODE).equals(ConstantProperty.OK_STATUS)) {
					jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
					jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
					log(clazz, "OTP NOT SENT", ConstantProperty.LOG_ERROR);

				}
			}
			else {
				jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
				jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
				log(clazz, "NOT ABLE TO GENERATE OTP", ConstantProperty.LOG_TRACE);

			}
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
			return sendResponse(jsonResponse);
		}
		return sendResponse(jsonResponse);
	}

	
	@RequestMapping(value = "/v1.0/validate", produces = { "application/json" },
					method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> validateOtp(HttpServletRequest request)
			throws Exception {
		User user = null;
		Employee emp = null;
		String mobileNumber = request.getParameter(ConstantProperty.MOBILE_NUMBER);
		String otp = request.getParameter(ConstantProperty.OTP);
		String deviceToken = request.getParameter(ConstantProperty.TOKEN);

		jsonResponse = new JsonResponse();
		try {
			OtpTransectionDetail otpTransectionDetail = otpDetailManager.getOtpDetails(otp, mobileNumber);
			if (otpTransectionDetail != null) {
				if (ValidateOtpExpiryTime(otpTransectionDetail)) {
					if(otpTransectionDetail.getUserType().equals(ConstantProperty.USER)) {
						user = authenticationManager.getUserByMobileNumber(mobileNumber);
						if (user == null) {
							user = getUserDetail(mobileNumber, otpTransectionDetail.getEmailId(), deviceToken);
							Long id = authenticationManager.saveUser(user);
							user.setId(id);
						} else {
							if (user.getEmailId()==null) user.setEmailId(otpTransectionDetail.getEmailId());
							user.setDeviceToken(deviceToken);
							user.setLastLoginDate(CommonUtil.getCurrentDate());
							user.setLoginCount(user.getLoginCount()+1);
							authenticationManager.saveUpdateUser(user);
						}

						String accessKey = JwtUtil.getRandomSecretKey();
						String accessToken = JwtTokenFactory.createAccessJwtToken(String.valueOf(user.getId()), accessKey);

						Long id = jwtTokenManager.createAccessToken(user, accessToken, accessKey);
						if (id != null) {
							jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
							jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_AUTHENTICATION);
							jsonResponse.setAccessToken(accessToken);
						} else {
							jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
							jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
							log(clazz, "NOT ABLE TO GENERATE ACCESS_TOKEN", ConstantProperty.LOG_DEBUG);
							return sendResponse(jsonResponse);
						}
					} else {
						emp  = employeeManager.getEmployee(mobileNumber);
						if (emp == null) {
							jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
							jsonResponse.setMessage(ConstantProperty.NOT_RESISTERED_EMPLOYEE);
							return sendResponse(jsonResponse);
						} else {
							emp.setDeviceToken(deviceToken);
							employeeManager.saveUpdateUser(emp);
						}

						String accessKey = JwtUtil.getRandomSecretKey();
						String accessToken = JwtTokenFactory.createAccessJwtToken(String.valueOf(emp.getId()), accessKey);

						Long id = jwtTokenManager.createAccessTokenEmployee(emp, accessToken, accessKey);
						if (id != null) {
							jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
							jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_AUTHENTICATION);
							jsonResponse.setAccessToken(accessToken);
						} else {
							jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
							jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
							log(clazz, "NOT ABLE TO GENERATE ACCESS_TOKEN", ConstantProperty.LOG_DEBUG);
							return sendResponse(jsonResponse);
						}
					}
					otpDetailManager.delete(otpTransectionDetail);
				} else {
					jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
					jsonResponse.setMessage(ConstantProperty.OTP_EXPIRED_MESSAGE);
					log(clazz, ConstantProperty.OTP_EXPIRED_MESSAGE, ConstantProperty.LOG_DEBUG);

				}
			} else {
				jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
				jsonResponse.setMessage(ConstantProperty.OTP_VALIDATION_FAILED);
				log(clazz, ConstantProperty.OTP_VALIDATION_FAILED, ConstantProperty.LOG_DEBUG);

			} 
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
			return sendResponse(jsonResponse);
		}
		return sendResponse(jsonResponse);
	}

	@RequestMapping(value = "/v1.0/sso/google", produces={"application/json"},
			consumes={"application/x-www-form-urlencoded"},
			method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> authenticateUserWithGoogle(HttpServletRequest request) throws Exception {
		jsonResponse = new JsonResponse();
		String token = request.getParameter(ConstantProperty.GMAIL_SIGNIN_TOKEN);
		try {
			final NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
			final JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
					.setAudience(Collections.singletonList(ConstantProperty.CLIENT_ID))
					.build();
			final GoogleIdToken googleIdToken = verifier.verify(token);

			if (googleIdToken == null) {
				jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
				jsonResponse.setMessage(ConstantProperty.AUTHENTICATION_FAILED); 
				log(clazz, ConstantProperty.AUTHENTICATION_FAILED, ConstantProperty.LOG_DEBUG);
				return sendResponse(jsonResponse);
			}
			final Payload payload = googleIdToken.getPayload();
			final Boolean emailVerified = payload.getEmailVerified();
			String emailId = payload.getEmail();
			User user = null;
			if (emailVerified) {
				if (emailId == null) {
					jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
					jsonResponse.setMessage(ConstantProperty.EMAIL_NOT_PRESENT_IN_ACCESS_CODE); 
					log(clazz, ConstantProperty.EMAIL_NOT_PRESENT_IN_ACCESS_CODE, ConstantProperty.LOG_DEBUG);
					return sendResponse(jsonResponse);
				}
				user = authenticationManager.getUserByEmailId(emailId);
				if (user == null) {
					jsonResponse.setStatusCode(ConstantProperty.NEW_USER);
					jsonResponse.setMessage(ConstantProperty.REGISTER_MOBILE_NUMBER_WITH_MAIL+":: "+emailId); 
					log(clazz, ConstantProperty.REGISTER_MOBILE_NUMBER_WITH_MAIL, ConstantProperty.LOG_DEBUG);
					return sendResponse(jsonResponse);
				} else {
					user.setLastLoginDate(CommonUtil.getCurrentDate());
					user.setLoginCount(user.getLoginCount()+1);
					authenticationManager.saveUpdateUser(user);
				}
				String accessKey = JwtUtil.getRandomSecretKey();
				String accessToken = JwtTokenFactory.createAccessJwtToken(String.valueOf(user.getId()), accessKey);

				Long id = jwtTokenManager.createAccessToken(user, accessToken, accessKey);
				if (id != null) {
					jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
					jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_AUTHENTICATION);
					jsonResponse.setAccessToken(accessToken);
				} else {
					jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
					jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
					log(clazz, "NOT ABLE TO GENERATE ACCESS_TOKEN", ConstantProperty.LOG_DEBUG);
				}
			} else {
				jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
				jsonResponse.setMessage(ConstantProperty.AUTHENTICATION_FAILED);
				log(clazz, ConstantProperty.AUTHENTICATION_FAILED, ConstantProperty.LOG_DEBUG);
			}
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
			return sendResponse(jsonResponse);
		}
		return sendResponse(jsonResponse);
	}
	
	@RequestMapping(value = "/v1.0/sso/facebook", produces={"application/json"},
			consumes={"application/x-www-form-urlencoded"},
			method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> authenticateUserWithFacebook(HttpServletRequest request) throws Exception {
		jsonResponse = new JsonResponse();
		String token = request.getParameter(ConstantProperty.FACEBOOK_SIGNIN_TOKEN);
		if (token == null || token.equals("")) {
			jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
			jsonResponse.setMessage(ConstantProperty.FACEBOOK_CODE_NOT_PRESENT); 
			log(clazz, ConstantProperty.FACEBOOK_CODE_NOT_PRESENT, ConstantProperty.LOG_DEBUG);
			return sendResponse(jsonResponse);
		}
		try {
			FBConnection fbConnection = new FBConnection();
			String accessToken = fbConnection.getAccessToken(token);

			FBGraph fbGraph = new FBGraph(accessToken);
			String graph = fbGraph.getFBGraph();
			Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
			String emailId = fbProfileData.get("email");
			if (emailId == null) {
				jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
				jsonResponse.setMessage(ConstantProperty.EMAIL_NOT_PRESENT_IN_ACCESS_CODE);
				log(clazz, ConstantProperty.EMAIL_NOT_PRESENT_IN_ACCESS_CODE, ConstantProperty.LOG_DEBUG);
				return sendResponse(jsonResponse);
			}
			User user = authenticationManager.getUserByEmailId(emailId);
        	if (user == null) {
        		jsonResponse.setStatusCode(ConstantProperty.NEW_USER);
				jsonResponse.setMessage(ConstantProperty.REGISTER_MOBILE_NUMBER_WITH_MAIL+":: "+emailId); 
				log(clazz, ConstantProperty.REGISTER_MOBILE_NUMBER_WITH_MAIL, ConstantProperty.LOG_DEBUG);
				return sendResponse(jsonResponse);
			} else {
				user.setLastLoginDate(CommonUtil.getCurrentDate());
				user.setLoginCount(user.getLoginCount()+1);
				authenticationManager.saveUpdateUser(user);
			}
        	String accessKey = JwtUtil.getRandomSecretKey();
			String appAccessToken = JwtTokenFactory.createAccessJwtToken(String.valueOf(user.getId()), accessKey);

			Long id = jwtTokenManager.createAccessToken(user, appAccessToken, accessKey);
			if (id != null) {
				jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
				jsonResponse.setMessage(ConstantProperty.SUCCESSFUL_AUTHENTICATION);
				jsonResponse.setAccessToken(appAccessToken);
			} else {
				jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
				jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
				log(clazz, "NOT ABLE TO GENERATE ACCESS_TOKEN", ConstantProperty.LOG_DEBUG);
			}
		} catch (Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
			jsonResponse.setMessage(ex.getMessage()); 
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
			return sendResponse(jsonResponse);
		}
		
        return sendResponse(jsonResponse);
	}

	@RequestMapping(value = "/v1.0/register/mobilenumber", produces={"application/json"},
			consumes={"application/x-www-form-urlencoded"},
			method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> registerMobileNumberWithEmail(HttpServletRequest request) throws Exception {
		jsonResponse = new JsonResponse();
		String mobileNumber = request.getParameter(ConstantProperty.MOBILE_NUMBER);
		String emailId = request.getParameter(ConstantProperty.EMAIL_ID);
		User user = null;
		if ((mobileNumber == null || mobileNumber.equals("")) && (emailId == null || emailId.equals(""))) {
			jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
			jsonResponse.setMessage(ConstantProperty.FACEBOOK_CODE_NOT_PRESENT); 
			log(clazz, ConstantProperty.FACEBOOK_CODE_NOT_PRESENT, ConstantProperty.LOG_DEBUG);
			return sendResponse(jsonResponse);
		}
		try {
			Long.valueOf(mobileNumber);
			user = authenticationManager.getUserByMobileNumber(mobileNumber);
			if(user!=null && user.getEmailId() != null) {
				jsonResponse.setStatusCode(ConstantProperty.ALREADY_EXIST_USER);
				jsonResponse.setMessage(ConstantProperty.ALREADY_REGISTER_MOBILE_NUMBER_WITH_MAIL); 
				log(clazz, ConstantProperty.ALREADY_REGISTER_MOBILE_NUMBER_WITH_MAIL, ConstantProperty.LOG_DEBUG);
				return sendResponse(jsonResponse);
			}
			String otp = CommonUtil.getRandomOtp();
			OtpTransectionDetail otpTransectionDetails = getOtpDetail(mobileNumber, otp, emailId, ConstantProperty.USER);
			Long id = otpDetailManager.saveOtpDetails(otpTransectionDetails);
			if(id != null) {
				jsonResponse.setStatusCode(ConstantProperty.OK_STATUS);
				jsonResponse.setMessage(ConstantProperty.OTP_SENT);
				String messageBody = SmsMessagesUtil.getMessageForOtp("1234");
				smsStatus = sendSMS.sendSms(mobileNumber, messageBody);
				if(!smsStatus.get(ConstantProperty.STATUS_CODE).equals(ConstantProperty.OK_STATUS)) {
					jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
					jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
					log(clazz, "OTP NOT SENT", ConstantProperty.LOG_ERROR);

				}
			}
			else {
				jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
				jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
				log(clazz, "NOT ABLE TO GENERATE OTP", ConstantProperty.LOG_TRACE);

			}
		} catch (Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.UNAUTHORIZED);
			jsonResponse.setMessage(ex.getMessage()); 
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
			return sendResponse(jsonResponse);
		}
		
        return sendResponse(jsonResponse);
	}

	private boolean ValidateOtpExpiryTime(OtpTransectionDetail otpTransectionDetails) throws Exception {
		Date currentDate = CommonUtil.getCurrentDate();
		Date otpExpiryDate = otpTransectionDetails.getExpirytimeStamp();
		return otpExpiryDate.after(currentDate);
	}
	
	public OtpTransectionDetail getOtpDetail(String mobileNumber, String otp, String emailId, String userType) throws Exception {
		OtpTransectionDetail otpTransectionDetails = new OtpTransectionDetail();
		otpTransectionDetails.setMobileNumber(mobileNumber);
		otpTransectionDetails.setEmailId(emailId);
		otpTransectionDetails.setOtp(otp);
		otpTransectionDetails.setUserType(userType);
		otpTransectionDetails.setExpirytimeStamp(CommonUtil.getExpiryDate());
		return otpTransectionDetails;
	}
	
	public User getUserDetail(String mobileNumber, String emailId, String token) throws Exception {
		User user = new User();
		user.setLoginCount(1);
		user.setLoginDate(CommonUtil.getCurrentDate());
		user.setMobileNumber(mobileNumber);
		user.setEmailId(emailId);
		user.setDeviceToken(token);
		return user;
	}
}
