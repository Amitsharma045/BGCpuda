package com.bitGallon.complaintMgmt.util;

public class SmsMessagesUtil {

	//OTP MESSAGE
	public static String getMessageForOtp(String otp){
		return "Your one time password(OTP) at CPUDA is "+otp+". Do not share this with anyone.";
	}

	//COMPLAINT REGISTRATION MESSAGES
	public static String getMessageForComplainer(String id){
		return "Your complaint has been lodged with CPUDA with complaint id "+id;
	}

	public static String getMessageForComplaintAsignee(String id){
		return "You have been assigned a complaint with complaint id "+id+". Please visit CPUDA Employee app to see the details.";
	}

	//AUTO ESCALATE MESSAGES 
	public static String getAutoEscalateMessageForComplainer(String id){
		return "Your complaint with complaint id "+id+" has been escalated to higher hierarchy. We sincerely regret the inconvenience caused.";
	}

	public static String getAutoEscalateMessageForPreviousComplaintAsignee(String id){
		return "Escalation : The complaint assigned to you with complaint id "+id+" has been escalated to higher hierarchy as it was not resolved in time.";
	}

	public static String getAutoEscalateMessageForNextComplaintAsignee(String id){
		return "Escalation Auto : You have been assigned with escalated complaint id "+id+". Please visit CPUDA Employee app to see the details.";
	}

	//MANUAL ESCALATION MESSAGE
	public static String getManualEscalateMessageForNextComplaintAsignee(String id){
		return "Escalation Manual : You have been assigned with escalated complaint id "+id+". Please visit CPUDA Employee app to see the details.";
	}

	//RESOLVED MESSAGES
	public static String getResolvedComplaintMessageForComplainer(String id){
		return "Your complaint with complaint id "+id+" has been resolved. Please visit CPUDA app to give your valuable feedback for resolved complaint.";
	}

}
