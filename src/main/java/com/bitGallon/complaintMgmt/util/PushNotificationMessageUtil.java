package com.bitGallon.complaintMgmt.util;

public class PushNotificationMessageUtil {

		public static String getMessageForComplaintAsignee(String id){
			return "You have been assigned a complaint with complaint id "+id;
		}

		//AUTO ESCALATE MESSAGES 
		public static String getAutoEscalateMessageForComplainer(String id){
			return "Your complaint with complaint id "+id+" has been escalated to higher hierarchy.";
		}

		public static String getAutoEscalateMessageForPreviousComplaintAsignee(String id){
			return "Escalation : The complaint assigned to you with complaint id "+id+" has been escalated to higher hierarchy.";
		}

		public static String getAutoEscalateMessageForNextComplaintAsignee(String id){
			return "Escalation Auto : You have been assigned with escalated complaint id "+id;
		}

		//MANUAL ESCALATION MESSAGE
		public static String getManualEscalateMessageForNextComplaintAsignee(String id){
			return "Escalation Manual : You have been assigned with escalated complaint id "+id;
		}

		//RESOLVED MESSAGES
		public static String getResolvedComplaintMessageForComplainer(String id){
			return "Your complaint with complaint id "+id+" has been resolved. Please provide your valuable feedback.";
		}
}
