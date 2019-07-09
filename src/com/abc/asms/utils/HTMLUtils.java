package com.abc.asms.utils;

import java.time.LocalDate;

public class HTMLUtils {


	public static String dateFormat(String saleDate) {

		String date = saleDate;
		if(saleDate != null) {
			LocalDate ld = LocalDate.parse(date);
			int year = ld.getYear();
			int month = ld.getMonthValue();
			int day = ld.getDayOfMonth();
			date = year + "/" + month + "/" + day;
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
