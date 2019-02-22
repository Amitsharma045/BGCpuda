package com.bitGallon.complaintMgmt.manager;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.notification.PushNotificationUtil;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.smsapi.sendSMS;
import com.bitGallon.complaintMgmt.util.PushNotificationMessageUtil;
import com.bitGallon.complaintMgmt.util.SmsMessagesUtil;
/**
 * @author rpsingh
 *
 */
@Service
public class PushNotificationManager {
	
	@Autowired
	private Environment env;
	
	private void pushFCMNotificationsUser(String userDeviceIdKey, JSONObject notification, JSONObject body) throws Exception {
		if (userDeviceIdKey!=null)
			PushNotificationUtil.pushFCMNotifications(env.getProperty("notification.firbase.user"), userDeviceIdKey, notification, body);
		else
			System.out.println("userDeviceIdKey can't be NULL");

	}
	private void pushFCMNotificationsEmployee(String employeeDeviceIdKey, JSONObject notification, JSONObject body) throws Exception {
		if (employeeDeviceIdKey!=null)
			PushNotificationUtil.pushFCMNotifications(env.getProperty("notification.firbase.employee"), employeeDeviceIdKey, notification, body);
		else
			System.out.println("employeeDeviceIdKey can't be NULL");
	}
	
	public void sendNewComplaintNotifications(ComplaintRegistration complaintRegistration) throws Exception {
		pushFCMNotificationsEmployee(complaintRegistration.getEmployee().getDeviceToken(),
				PushNotificationUtil.getNotification(ConstantProperty.NEW_COMPLAINT_TITLE,
						PushNotificationMessageUtil.getMessageForComplaintAsignee(complaintRegistration.getReferenceComplaint())), 
				PushNotificationUtil.getData(complaintRegistration.getReferenceComplaint(), complaintRegistration.getStatus().getStatus()));
		sendSMS.sendSms(complaintRegistration.getUser().getMobileNumber(), SmsMessagesUtil.getMessageForComplainer(complaintRegistration.getReferenceComplaint()));
		sendSMS.sendSms(complaintRegistration.getEmployee().getRegisteredMobileNo(), SmsMessagesUtil.getMessageForComplaintAsignee(complaintRegistration.getReferenceComplaint()));	
	}
	
	public void sendResolvedComplaintNotifications(ComplaintRegistration complaintRegistration) throws Exception {
		pushFCMNotificationsUser(complaintRegistration.getUser().getDeviceToken(),
				PushNotificationUtil.getNotification(ConstantProperty.RESOLVED_COMPLAINT_TITLE,
						PushNotificationMessageUtil.getResolvedComplaintMessageForComplainer(complaintRegistration.getReferenceComplaint())),
				PushNotificationUtil.getData(complaintRegistration.getReferenceComplaint(), complaintRegistration.getStatus().getStatus()));
		
		sendSMS.sendSms(complaintRegistration.getUser().getMobileNumber(), SmsMessagesUtil.getResolvedComplaintMessageForComplainer(complaintRegistration.getReferenceComplaint()));
	}
	
	public void sendManualEscalationComplaintNotifications(ComplaintRegistration complaintRegistration) throws Exception {
		sendEscalationComplaintNotificationsToUser(complaintRegistration);
		sendManualEscalationComplaintNotificationsToEmployee(complaintRegistration);
	}
	
	public void sendEscalationComplaintNotificationsToUser(ComplaintRegistration complaintRegistration) throws Exception {
		pushFCMNotificationsUser(complaintRegistration.getUser().getDeviceToken(),
				PushNotificationUtil.getNotification(ConstantProperty.ESCALETD_COMPLAINT_TITLE,
						PushNotificationMessageUtil.getAutoEscalateMessageForComplainer(complaintRegistration.getReferenceComplaint())),
						PushNotificationUtil.getData(complaintRegistration.getReferenceComplaint(), complaintRegistration.getStatus().getStatus()));
		sendSMS.sendSms(complaintRegistration.getUser().getMobileNumber(), SmsMessagesUtil.getAutoEscalateMessageForComplainer(complaintRegistration.getReferenceComplaint()));
	}

	public void sendManualEscalationComplaintNotificationsToEmployee(ComplaintRegistration complaintRegistration) throws Exception {
		pushFCMNotificationsEmployee(complaintRegistration.getEmployee().getDeviceToken(),
				PushNotificationUtil.getNotification(ConstantProperty.ESCALETD_COMPLAINT_TITLE,
						PushNotificationMessageUtil.getManualEscalateMessageForNextComplaintAsignee(complaintRegistration.getReferenceComplaint())),
				PushNotificationUtil.getData(complaintRegistration.getReferenceComplaint(), complaintRegistration.getStatus().getStatus()));
		sendSMS.sendSms(complaintRegistration.getEmployee().getRegisteredMobileNo(), SmsMessagesUtil.getManualEscalateMessageForNextComplaintAsignee(complaintRegistration.getReferenceComplaint()));
	}
	
	public void sendAutoEscalationComplaintNotificationsToEmployee(ComplaintRegistration complaintRegistration) throws Exception {
		pushFCMNotificationsEmployee(complaintRegistration.getEmployee().getDeviceToken(),
				PushNotificationUtil.getNotification(ConstantProperty.ESCALETD_COMPLAINT_TITLE,
						PushNotificationMessageUtil.getAutoEscalateMessageForNextComplaintAsignee(complaintRegistration.getReferenceComplaint())),
				PushNotificationUtil.getData(complaintRegistration.getReferenceComplaint(), complaintRegistration.getStatus().getStatus()));
		sendSMS.sendSms(complaintRegistration.getEmployee().getRegisteredMobileNo(), SmsMessagesUtil.getAutoEscalateMessageForNextComplaintAsignee(complaintRegistration.getReferenceComplaint()));
	}
	
	public void sendAutoEscalateMessageForPreviousComplaintAsignee(ComplaintRegistration complaintRegistration) throws Exception {
		pushFCMNotificationsEmployee(complaintRegistration.getEmployee().getDeviceToken(),
				PushNotificationUtil.getNotification(ConstantProperty.ESCALETD_COMPLAINT_TITLE,
						PushNotificationMessageUtil.getAutoEscalateMessageForPreviousComplaintAsignee(complaintRegistration.getReferenceComplaint())),
				PushNotificationUtil.getData(complaintRegistration.getReferenceComplaint(), complaintRegistration.getStatus().getStatus()));
		sendSMS.sendSms(complaintRegistration.getEmployee().getRegisteredMobileNo(), SmsMessagesUtil.getAutoEscalateMessageForPreviousComplaintAsignee(complaintRegistration.getReferenceComplaint()));
	}
}
