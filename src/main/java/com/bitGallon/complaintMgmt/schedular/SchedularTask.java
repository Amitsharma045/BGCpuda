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
import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.EscalationHierarchy;
import com.bitGallon.complaintMgmt.manager.ComplaintManager;
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
	private EmployeeRepository empRepository;
	
	@Autowired
	private EscalationHierarchyRepository escalationHierarchyRepository;
	
	@Scheduled(fixedRate = 900000)
	public void assignEmployeeToComplaint() throws Exception {
		List<Employee> empList = null;
		boolean complaintStatus = false;
		System.out.println("Testing Schedular");
		if(isAssignEmployeeTask()) {
			List<ComplaintRegistration> complaintlist = complaintRepository.getAllUnAssiginedComplaint();
			if(complaintlist != null) {
				for(ComplaintRegistration complaintRegistration : complaintlist) {
					empList = empRepository.getEmployee(complaintRegistration.getIssueType().getRole(), complaintRegistration.getArea());
					if(empList != null) {
						HashMap<Employee, Integer> empCountHM = complaintRepository.getAssignedEmployee(empList);
						Employee assignedEmployee = CommonUtil.findAssignedEmployee(empCountHM);
						complaintRegistration.setEmployee(assignedEmployee);
						EscalationHierarchy escalationHierarchy = escalationHierarchyRepository.getEscalationHierarchyDetail(complaintRegistration.getIssueType(), (short)0);
						complaintRegistration.setEscalatedTime(CommonUtil.getEscaltedTime(escalationHierarchy.getEscalationTime()));
						complaintRepository.saveOrUpdateComplaintRegistration(complaintRegistration);
						System.out.println("Complaint Assigned");
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

	/*@Scheduled(fixedRate = 5000)
	public void escalateComplaint() {
		System.out.println("The time is now {}"+ dateFormat.format(new Date()));
	}*/

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

	public static boolean isAssignEmployeeTask() {
		return assignEmployeeTask;
	}

	public static void setAssignEmployeeTask(boolean assignEmployeeTask) {
		SchedularTask.assignEmployeeTask = assignEmployeeTask;
	}
}
