package com.abc.asms.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class HTMLUtils {

	/**
	 * 日付をyyyy/m/d形式にする。日付ではない場合そのまま返す。
	 * @param saleDate
	 * @return date ex."2019/2/3" または引数のまま
	 */
	public static String dateFormat(String saleDate) {

		String date = saleDate;
		if(saleDate != null && !saleDate.isEmpty()) {
			try {
				LocalDate ld = LocalDate.parse(date);
				int year = ld.getYear();
				int month = ld.getMonthValue();
				int day = ld.getDayOfMonth();
				date = year + "/" + month + "/" + day;
			} catch (Exception e) {}
		}
		return date;
	}

	/**
	 * _header.jspのリンクにactiveのクラスを付けるメソッド
	 * @param buttonName リンクの種類
	 * @param current 現在開いているページの種類
	 * @return クラス または ""
	 */
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
	public static String writtenChecked(String now,String[] list) {
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
	/**
	 * listの中身の文字列と検索文字列が一致したら"errorMessage"を返す
	 * 一致しなければ""(空文字)を返す。
	 * @param error	 エラー文
	 * @param matches 検索する文字列
	 * @return "errorMessage" 又は ""
	 */
	public static String errorMessage(List<String> error,String matches) {
		if(error!=null&&0<error.size()) {
			for(String text:error) {
				if(text.indexOf(matches)!=-1) {
					return "errorMessage";
				}
			}
		}
		return "";
	}
	/**
	 * listの中身の文字列と検索文字列が一致したら"errorFrame"を返す
	 * 一致しなければ""(空文字)を返す。
	 * @param error	 エラー文
	 * @param matches 検索する文字列
	 * @return "errorFrame" 又は ""
	 */
	public static String errorFrame(List<String> error,String matches) {
		if(error!=null&&0<error.size()) {
			for(String text:error) {
				if(text.indexOf(matches)!=-1) {
					return "errorFrame";
				}
			}
		}
		return "";
	}
	/**
	 * listの中身の文字列と正規表現文字列が一致したら"errorFrame"を返す
	 * 一致しなければ""(空文字)を返す。
	 * @param error	 エラー文
	 * @param matches 検索する文字列
	 * @return "errorFrame" 又は ""
	 */
	public static String regexpErrorFrame(List<String> error,String matches) {
		if(error!=null&&0<error.size()) {
			for(String text:error) {
				if(text.matches(matches)) {
					return "errorFrame";
				}
			}
		}
		return "";
	}

	/**
	 * listの中身の文字列と正規表現文字列が一致したら"errorMessage"を返す
	 * 一致しなければ""(空文字)を返す。
	 * @param error	 エラー文
	 * @param matches 検索する文字列
	 * @return "errorMessage" 又は ""
	 */
	public static String regexpErrorMessage(List<String> error,String matches) {
		if(error!=null&&0<error.size()) {
			for(String text:error) {
				if(text.matches(matches)) {
					return "errorMessage";
				}
			}
		}
		return "";
	}
	/**
	 * Authorityのcheck属性の設定メソッドです
	 * @param now 現在のinputタグの値(all,no,yes)
	 * @param authority saleAuthority又はaccountAuthorityのパラメータ値
	 * @return authorityとnowが一致した時 か、<br>
	 * nowがallかつauthrityが指定以外の値の時(初期と例外時)<br>
	 * "checked"の出力、それ以外は空文字
	 */
	public static String checkedAuthority(String now,String authority){
		if (authority==null) {authority="";}
		if(authority.equals(now)) {
			return "checked";
		}else if(now.equals("all")&&!(authority.equals("all")||authority.equals("yes")||authority.equals("no"))) {
			return "checked";
		}
		return "";
	}

	/**
	 * 数字の場合はカンマ区切りに、そうでない場合はそのまま返す。
	 * @param num 数字の文字列
	 * @return カンマ区切りの数字 または引数のまま
	 */
	public static String numberFormat(String num) {

		if(num != null && num.matches("^[0-9]+$")) {
			return String.format("%,d", Long.parseLong(num));
		}else {
			return num;
		}
	}

	/**
	 * XSS(クロスサイトスクリプティング)対策の置換処理
	 * [&,<,>,',"]をそれぞれのエスケープに置換する
	 * @param text 置換前の文字列
	 * @return XSS対策の置換処理後の文字列
	 */
	public static String XSS(String text) {
		text = text.replace("&", "&amp;");
		text = text.replace("<", "&lt;");
		text = text.replace(">", "&gt;");
		text = text.replace("'", "&#39;");
		return text.replace("\"", "&quot;");

	}

	/**
	 * writeCheckedのオーバーロード
	 * @param val タグのvalue値
	 * @param form 受け取った値
	 * @return "checked" または ""
	 */
	public static String writeChecked(String val,String form) {
		if(form != null) {
			if(form.equals(val)){
				return "checked";
			}
		}
		return "";
	}

	/**
	 * writeCheckedのオーバーロード
	 * @param val タグのvalue値(int)
	 * @param form 受け取った値
	 * @return "checked" または ""
	 */
	public static String writeChecked(int val,String form) {
		if(form != null && form.matches("^[0-9]+$")) {
			if(Integer.valueOf(form) == val){
				return "checked";
			}
		}
		return "";
	}

	/**
	 * writeSelectedのオーバーロード
	 * @param val タグのvalue値(int)
	 * @param form 受け取った値
	 * @return "selected" または ""
	 */
	public static String writeSelected(int val,String form) {
		if(form != null && form.matches("^[0-9]+$")) {
			if(Integer.valueOf(form) == val){
				return "selected";
			}
		}
		return "";
	}


	/**
	 * S0022.jspで使用します。
	 * 備考の改行をHTMLに反映させます。
	 * @param text
	 * @return <br>タグ
	 */
	public static String newLine(String text) {
		text = text.replace("\r\n", "<br>");
		return text;
	}

	public static String setNewLine(String name) {

		if(name.length() <= 7) {
			return name;
		}
		List<String> list = new ArrayList<>();
		for(int i = 0; i < name.length(); i += 7) {
			if(i + 7 < name.length()) {
				list.add(name.substring(i, i + 7));
			}else {
				list.add(name.substring(i));
			}
		}
		String name2 = "";
		for(String s : list) {
			name2 += (s + "\r\n");
		}
		return name2;
	}

	/**
	 * 定数「ROUNDING_MODE」によって、丸め方を切り替えるメソッド
	 * @return 丸め後の数字
	 */
	public static String rounding(String num) {
		return new BigDecimal(num).setScale(0, ConstantUtils.ROUNDING_MODE).toString();
	}

	public static String S0022_1(String historyId,LinkedHashMap<Integer, String> histories) {
		List<Integer> list = new ArrayList<>(histories.keySet());
		int i = list.size()-1;
		if(historyId.equals(list.get(i).toString())) {
			return "作成";
		}
		return "編集";
	}
}