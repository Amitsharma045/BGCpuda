package com.bitGallon.complaintMgmt.schedular;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.entity.ComplaintStatus;
import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.EscalationHierarchy;
import com.bitGallon.complaintMgmt.manager.ComplaintManager;
import com.bitGallon.complaintMgmt.manager.PushNotificationManager;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.repository.ComplaintRepository;
import com.bitGallon.complaintMgmt.repository.EmployeeRepository;
import com.bitGallon.complaintMgmt.repository.EscalationHierarchyRepository;
import com.bitGallon.complaintMgmt.util.CommonUtil;

@Component
public class SchedularTask {

	private Class clazz = SchedularTask.class;

	private static boolean assignEmployeeTask = true;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private ComplaintManager complaintManager;
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired
	private EscalationHierarchyRepository escalationHierarchyRepository;
	
	@Autowired
	private  PushNotificationManager pushNotificationManager;
	
	@Scheduled(fixedRate = 900000)
	public void assignEmployeeToComplaint() throws Exception {
		List<Employee> empList = null;
		boolean complaintStatus = false;
		System.out.println("Complaint Assignment Schedular");
		if(isAssignEmployeeTask()) {
			List<ComplaintRegistration> complaintlist = complaintRepository.getAllUnAssiginedComplaints();
			if(complaintlist != null) {
				for(ComplaintRegistration complaintRegistration : complaintlist) {
					empList = empRepository.getEmployee(complaintRegistration.getIssueType().getRole(), complaintRegistration.getArea());
					if(empList != null) {
						Employee previousEmployee = complaintRegistration.getEmployee();
						HashMap<Employee, Integer> empCountHM = complaintRepository.getAssignedEmployee(empList);
						Employee assignedEmployee = CommonUtil.findAssignedEmployee(empCountHM);
						complaintRegistration.setEmployee(assignedEmployee);
						EscalationHierarchy escalationHierarchy = escalationHierarchyRepository.getEscalationHierarchyDetail(complaintRegistration.getIssueType(), (short)0);
						complaintRegistration.setEscalatedTime(CommonUtil.getEscaltedTime(escalationHierarchy.getEscalationTime()));
						complaintRepository.saveOrUpdateComplaintRegistration(complaintRegistration);
						System.out.println("Complaint Assigned");
						pushNotificationManager.sendEscalationComplaintNotificationsToUser(complaintRegistration);
						pushNotificationManager.sendAutoEscalationComplaintNotificationsToEmployee(complaintRegistration);
						complaintRegistration.setEmployee(previousEmployee);
						pushNotificationManager.sendAutoEscalateMessageForPreviousComplaintAsignee(complaintRegistration);
					} else {
						System.out.println("Complaint Not Assigned Yet");
						SchedularTask.setAssignEmployeeTask(true);
						complaintStatus = true;
					}
				}
			} else {
				SchedularTask.setAssignEmployeeTask(false);
			}
			if(!complaintStatus) SchedularTask.setAssignEmployeeTask(false);
		}
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
	 
	@Scheduled(fixedRate = 300000)
	public void escalateComplaint() {
		List<ComplaintRegistration> escalatedComplaints = complaintRepository.getCrossedEscalatedTimeComplaints();
		if (escalatedComplaints != null && !escalatedComplaints.isEmpty()) {
			for (ComplaintRegistration complaint : escalatedComplaints) {
				complaintManager.escalateComplaints(complaint);
			}
		}
		else {
			log(clazz, "No escalation required at "+new Date(), ConstantProperty.LOG_INFO);
		}
		System.out.println(escalatedComplaints);
	}

	public static boolean isAssignEmployeeTask() {
		return assignEmployeeTask;
	}

	public static void setAssignEmployeeTask(boolean assignEmployeeTask) {
		SchedularTask.assignEmployeeTask = assignEmployeeTask;
	}
}
