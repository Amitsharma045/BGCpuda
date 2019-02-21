package com.bitGallon.complaintMgmt.property;
/**
 * @author rpsingh
 *
 */
public class ConstantProperty {

	public static final String OK_STATUS  = "200";
	public static final String UNAUTHORIZED  = "401";
	public static final String METHOD_FAILURE = "420";
	public static final String FILE_SIZE_LIMIT  = "421";
	public static final String INVALID_FILE  = "422";
	public static final String SERVER_ERROR  = "501";
	public static final String NEW_USER = "800";
	public static final String ALREADY_EXIST_USER = "600";

	public static final String MOBILE_NUMBER  = "mobileNumber";
	public static final String EMAIL_ID  = "emailId";
	public static final String NAME  = "name";
	public static final String OTP  = "otp";
	public static final String TOKEN  = "token";
	public static final String REGISTERED_COMPLAINT  = "registeredComplaint";
	public static final String COMPLAINT_NUMBER  = "complaintNumber";
	public static final String SERVICE_RATING  = "serviceRating";
	public static final String SERVICE_COMMENT  = "serviceComment";
	public static final String RECOMMENDATION_POINT  = "recommendationPoint";
	public static final String ASPECT_RATING  = "aspectRating";


	public static final String STATUS_CODE  = "statusCode";
	public static final String MESSAGE  = "message";
	public static final String ACCESS_TOKEN  = "accessToken";
	public static final String USER_DETAIL = "userDetail";
	public static final String REFRESH_TOKEN  = "refreshToken";
	public static final String UPDATE_FLAG  = "updateFlag";


	public static final String COMPLAINTS  = "complaints";
	public static final String LOCATIONS  = "locations";
	public static final String LOCATION_LIST  = "locationsList";
	public static final String CATEGORY_LIST  = "categoryList";



	public static final String ACTION_REQUIRED  = "actionRequired";
	public static final String COMPLAINT_LIST  = "complaintsList";
	public static final String TOTAL_COUNT  = "totalCount";
	public static final String COMPLAINT_TITLE  = "complaintTitles";



	public static final String SUCCESSFUL_AUTHENTICATION  = "Successful Authentication";
	public static final String AUTHENTICATION_FAILED  = "Authentication Failed";
	public static final String FACEBOOK_CODE_NOT_PRESENT  =  "Didn't get code parameter in callback";
	public static final String SUCCESSFUL_PROCESSED  = "Successful Processed ";

	public static final String OTP_EXPIRED_MESSAGE  = "Otp Expired";
	public static final String OTP_VALIDATION_FAILED  = "Otp validation Failed";
	public static final String NOT_VALID_URL  = "Not Valid URL";

	public static final String INTERNAL_SERVER_ERROR  = "Internal Server Error";
	public static final String OTP_SENT  = "Otp sent on registered Mobile";
	public static final String OTP_FAILED  = "Otp Not Sent";

	public static final String ALREADY_REGISTED_USER  = "Already Registered Mobile Number";
	public static final String NOT_RESISTERED_USER  = "Not Exsit User";
	public static final String NOT_RESISTERED_EMPLOYEE  = "Not Resgisterd Employee";

	public static final String EMAIL_NOT_PRESENT_IN_ACCESS_CODE  = "Email Not Present In AccessCode";
	
	public static final String LOGIN  = "login";
	public static final String SIGN_UP  = "signup";
	
	public static final String MOBILE_SIGN_UP  = "Mobile";
	public static final String FACEBOOK_SIGN_UP  = "FaceBook";
	public static final String GOOGLE_SIGN_UP  = "Google";


	public static final String FACEBOOK_SIGNIN_TOKEN  = "code";
	public static final String GMAIL_SIGNIN_TOKEN  = "idToken";
	
	public static final String LAST_ID  = "lastID";
	public static final String STATE_ID  = "stateID";
	public static final String DISTRICT_ID  = "districtID";
	public static final String CITY_ID  = "cityID";

	public static final String FILE_NOT_EXIST = "File doesn't exist please check with Admin";
	public static final String FILE_OVERRIDE_ERROR = "File already exist, please re-name the file and upload again";
	public static final String SUCCESSFUL_SAVED = "Information saved successfully";
	public static final String FAILURE_NOT_SAVED = "Information couldn't saved successfully, please re-try";
	public static final String FILE_SIZE_ERROR = "Overall file size should be less than 25Mb";
	public static final String INVALID_FILE_ERROR = "Accepted file format are .jpg, .jpeg, .png, .pdf, .docs & .xls";
	public static final long MAX_FILES_LIMIT = 26214400l;
	public static final int MAX_RANDOM_NUM = 8888889;
	public static final int MIN_RANDOM_NUM = 1111111;
	
	public static final String ATTACHMENT_LIST = "attachments";
	public static final String IMAGE_LIST = "images";

	public static final String LOG_INFO = "INFO";
	public static final String LOG_DEBUG = "DEBUG";
	public static final String LOG_ERROR = "ERROR";
	public static final String LOG_WARNING = "WARNING";
	public static final String LOG_TRACE = "TRACE";
	public static final String LOGOUT = "Successfully Logout";

	public static final String CLIENT_ID = "clientId";

	public static final String REGISTER_MOBILE_NUMBER_WITH_MAIL  = "Register Mobile Number With Mail Id";
	public static final String ALREADY_REGISTER_MOBILE_NUMBER_WITH_MAIL  = "Already Register Mobile Number With Mail Id";

	public static final String STATUS_RESOLVED = "RESOLVED";
	public static final String STATUS_IN_PROGRESS = "IN PROGRESS";
	public static final String STATUS_ESCALATED = "ESCALATED";
	public static final String STATUS_CLOSED = "CLOSED";

	public static final String SUB_STATUS_RESOLVED_ISSUE_FIXED = "ISSUE FIXED";
	public static final String SUB_STATUS_RESOLVED_NOT_AN_ISSUE = "NOT AN ISSUE";
	public static final String SUB_STATUS_RESOLVED_NOT_DEPARTMENTAL_ISSUE = "NOT DEPARTMENTAL ISSUE";
	public static final String SUB_STATUS_RESOLVED_OTHERS = "OTHERS";

	public static final String SUB_STATUS_IN_PROGRESS = "IN PROGRESS";
	
	public static final String SUB_STATUS_ESCALED_ESCALATED_BY_SYSTEM = "ESCALATED BY SYSTEM";
	public static final String SUB_STATUS_ESCALED_ESCALATED_BY_EMPLOYEE = "ESCALATED BY EMPLOYEE";
	public static final String SUB_STATUS_ESCALED_NEED_APPROVAL = "NEED APPROVAL";
	public static final String SUB_STATUS_ESCALED_STOCK_UNAVAILABLE = "STOCK UNAVAILABLE";
	public static final String SUB_STATUS_ESCALED_REQUIRE_MORE_TIME = "REQUIRE MORE TIME";
	public static final String SUB_STATUS_ESCALED_OTHERS = "OTHERS";
	
	public static final String SUB_STATUS_CLOSED_FEEDBACK_PROVIDED = "CLOSED";

	
	public static final String ISSUE_TITLE = "issueTitle";
	public static final String ISSUE_TYPE = "issueType";
	public static final String AREA_NAME = "areaName";
	public static final String COMPLAINT_LATITUDE = "complaintLatitude";
	public static final String COMPLAINT_LOGITUDE = "complaintLogitude";
	public static final String LANDMARK = "landmark";

	public static final String EMPLOYEE  = "EMPLOYEE";
	public static final String USER  = "USER";

	public static final String NEW_COMPLAINT_TITLE = "New Complaint";
	public static final String ESCALETD_COMPLAINT_TITLE = "Escaleted Complaint";
	public static final String RESOLVED_COMPLAINT_TITLE = "Resolved Complaint";
	



}
