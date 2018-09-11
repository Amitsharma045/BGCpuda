package com.bitGallon.complaintMgmt.repository;

public class UtilRepository {
	public static String getIsActiveQuery(String aliasName) {
		return " " +aliasName+".isActive = 1";
	}
}
