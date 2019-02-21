package com.bitGallon.complaintMgmt.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.commons.lang.time.DateUtils;

import com.bitGallon.complaintMgmt.entity.ComplaintStatus;
import com.bitGallon.complaintMgmt.entity.Employee;
import com.google.common.net.MediaType;

/**
 * @author rpsingh
 *
 */
public class CommonUtil {
	
	public static String getRandomOtp() throws Exception {
		Random rand = new Random();
//		return String.format("%04d", rand.nextInt(10000));
		return "1234";
	} 
	
	public static Date getExpiryDate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	public static Date getCurrentDate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
	
	public static boolean isEmpty(String s, boolean trim) throws Exception {
		if (s != null) {
			if (trim) return (s.trim().length() == 0);
			return (s.length() == 0);
		} else {
			return true;
		}
	}
	
	public static  boolean ValidateExpiryTime(Date currentDate,Date expiryDate) throws Exception {
		return expiryDate.after(currentDate);
	}
	
	public static String getYearMonth() throws Exception {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.get(Calendar.MONTH);
		return String.valueOf(calendar.get(Calendar.YEAR)) + calendar.get(Calendar.MONTH);
	}
	
	public static String getRandomNumber() throws Exception {
		Random rand = new Random();
		return String.format("%04d", rand.nextInt(10000));
	}
	
	public static String getEncryptUserDetail(String userId) throws Exception {
		return AESEncryptionAlgo.encrypt(userId);
	}

	public static List<MediaType> listOfAcceptedFiles() throws Exception {
		List<MediaType> acceptedLists= new ArrayList<>();
		acceptedLists.add(MediaType.MICROSOFT_WORD);
		acceptedLists.add(MediaType.ANY_AUDIO_TYPE);
		acceptedLists.add(MediaType.ANY_VIDEO_TYPE);
		acceptedLists.add(MediaType.MICROSOFT_EXCEL);
		acceptedLists.add(MediaType.JPEG);
		acceptedLists.add(MediaType.BMP);
		acceptedLists.add(MediaType.PNG);
		acceptedLists.add(MediaType.PDF);
		return acceptedLists;
	}
	
	public static boolean getMatchingStrings(List<MediaType> list, String regex) throws Exception {
		  for (MediaType s:list) {
		    if(MediaType.parse(regex).is(s))
		    	return true;
		  }

		  return false;
		}
	
	public static boolean isGetter(Method method) {
		   if (Modifier.isPublic(method.getModifiers()) &&
		      method.getParameterTypes().length == 0) {
		         if (method.getName().matches("^get[A-Z].*") &&
		            !method.getReturnType().equals(void.class))
		               return true;
		         if (method.getName().matches("^is[A-Z].*") &&
		            method.getReturnType().equals(boolean.class))
		               return true;
		   }
		   return false;
		}
	
	public static Date getEscaltedTime(short hour) {
		Date currentDate = new Date();
		return DateUtils.addHours(currentDate, hour);
	}
	
	public static Employee findAssignedEmployee(HashMap<Employee, Integer> empHM) {
		int temp = (int) empHM.values().toArray()[0];
		Employee emp = empHM.keySet().stream().findFirst().get();
		for (Entry<Employee, Integer> entry : empHM.entrySet()) {
			if(entry.getValue() < temp) {
				emp = entry.getKey();
				temp = entry.getValue();
			} 
		}
		return emp;
	}

	public static String addPreviousComplaintStatus(String additionalComments, ComplaintStatus status,
			ComplaintStatus subStatus) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 3);
		System.out.println(cal);
		String pastComment = "Status included by previous employee " + status.getStatus() + " due to " + subStatus.getStatus();
		if(null != additionalComments) return pastComment;
		return additionalComments + " "+ pastComment;
	}
}
