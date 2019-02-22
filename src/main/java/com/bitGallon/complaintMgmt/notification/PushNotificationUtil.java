package com.bitGallon.complaintMgmt.notification;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONObject;

public class PushNotificationUtil {

	private static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	public static void pushFCMNotifications(String authKeyserver, String userDeviceIdKey, 
			                                      JSONObject notification, JSONObject data) throws Exception
	{

		URL url = new URL(API_URL_FCM);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization","key="+authKeyserver);
		conn.setRequestProperty("Content-Type","application/json");

		JSONObject json = new JSONObject();
		json.put("to",userDeviceIdKey.trim());
		json.put("notification", notification);
		json.put("data", data);

		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		System.out.println("request :: "+json.toString());
		wr.flush();
		InputStream is = conn.getInputStream();

		InputStreamReader isr  = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// receive response
		final StringBuilder sb = new StringBuilder();
		for (String line; (line = br.readLine()) != null;) {
			sb.append(line);
		}
		final String responseText = sb.toString();
		System.out.println("response:\n" + responseText);
	}
	
	public static JSONObject getNotification(String title, String body) {
		JSONObject info = new JSONObject();
		info.put("title", title); // Notification title
		info.put("body", body); 
		return info;
	}
	
	public static JSONObject getData(String complaintId, String complaintStatus) {
		JSONObject info = new JSONObject();
		info.put("complaintId", complaintId); 
		info.put("complaintStatus", complaintStatus); 
		return info;
	}
	
	
}
