package com.bitGallon.complaintMgmt.smsapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.bitGallon.complaintMgmt.property.ConstantProperty;

 
/**
 * @author suhani
 *
 */
public class sendSMS {

	public static HashMap<String,String> sendSms(String mobileNumber,String messageBody) {
		HashMap<String,String> smsStatus = new HashMap<String,String>();
		try {
			if(mobileNumber == null)
				throw new NullPointerException("Mobile Number can't be empty");
			String url="http://api.msg91.com/api/v2/sendsms?"
					+ "message="+messageBody
					+"&authkey=263706AzUVaNGZk35c6bb8de" 
					+"&mobiles="+mobileNumber 
					+"&route=4" //ROUTE 4 FOR TRANSCATIONAL MESSAGE
					+"&sender=CTPUDA" //SENDERID
					+"&country=91"; //COUNTRY CODE;


			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			int responseCode = conn.getResponseCode();
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			System.out.println("SMS Response : "+response.toString());
			smsStatus.put(ConstantProperty.STATUS_CODE, ConstantProperty.OK_STATUS);
			smsStatus.put(ConstantProperty.MESSAGE, response.toString());
			return smsStatus;
		} catch (Exception e) {
			System.out.println("exception came");
			System.out.println("SMS Response : "+e.getMessage());

			smsStatus.put(ConstantProperty.STATUS_CODE, ConstantProperty.SERVER_ERROR);
			smsStatus.put(ConstantProperty.MESSAGE, e.getMessage());
			return smsStatus;
		}
	}
}