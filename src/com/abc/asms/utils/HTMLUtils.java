package com.abc.asms.utils;

public class HTMLUtils {


	public static String dateFormat(String saleDate) {

		String date = saleDate;
		if(saleDate != null) {
			date = saleDate.replace("-", "/");
		}

		return date;
	}

	public static String createHeaderClass(String buttonName, String current) {

		if(buttonName.equals(current)) {
			return "class=\"active\"";
		}
		return "";
	}
}
