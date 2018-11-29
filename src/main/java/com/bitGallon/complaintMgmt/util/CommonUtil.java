package com.bitGallon.complaintMgmt.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;

import com.google.common.net.MediaType;

/**
 * @author rpsingh
 *
 */
public class CommonUtil {
	
	public static String getRandomOtp() throws Exception {
		Random rand = new Random();
		return String.format("%04d", rand.nextInt(10000));
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
		return DateUtils.addHours(currentDate, 3);
	}
}
