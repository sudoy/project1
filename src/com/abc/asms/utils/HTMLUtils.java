package com.abc.asms.utils;

import java.time.LocalDate;

public class HTMLUtils {


	public static String dateFormat(String saleDate) {

		String date = saleDate;
		if(saleDate != null && !saleDate.isEmpty()) {
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

	/**
	 * textとnowが等しい時に"selected"を返すメソッドです
	 * @param text 比較対象
	 * @param now 現在の値
	 * @return "selected"又は""(空文字)
	 */
	public static String writeSelected(String now,String text) {
		if(text!=null) {
			if(text.equals(now)){
				return "selected";
			}
		}
		return "";
	}
	/**
	 * Stringの配列と現在の値が等しい時に"checked"を返すメソッドです。
	 * listが存在しない場合はすべてで"checked"を返します。
	 * @param now 現在の値
	 * @param list 配列の値
	 * @return "checked"又は""(空文字)
	 */
	public static String writeChecked(String now,String[] list) {
		if(list!=null) {
			for(String c:list) {
				if(c.equals(now)){
					return "checked";
				}
			}
			return "";
		}else {
			return "checked";
		}

	}
}
