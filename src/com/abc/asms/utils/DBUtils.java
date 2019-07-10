package com.abc.asms.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {

	//DB接続
	public static Connection getConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("asms");
		return ds.getConnection();

	}

	//DB閉じる
	public static void close(Connection c,PreparedStatement p,ResultSet r){
		try {
			if(r != null){
				r.close();
			}
			if(p != null){
				p.close();
			}
			if(c != null){
				c.close();
			}
		} catch (Exception e) {}
	}
	public static void close(Connection c,PreparedStatement p){
		try {
			if(p != null){
				p.close();
			}
			if(c != null){
				c.close();
			}
		} catch (Exception e) {}

	}
	public static void close(PreparedStatement p,ResultSet r){
		try {
			if(p != null){
				p.close();
			}
			if(r != null){
				r.close();
			}
		} catch (Exception e) {}

	}
	public static void close(Connection... con){
		for(Connection c : con) {
			try{
				if(c != null){ c.close(); }
			}catch(Exception e) {}
		}
	}
	public static void close(PreparedStatement... ps){
		for(PreparedStatement p : ps) {
			try{
				if(p != null){ p.close(); }
			}catch(Exception e) {}
		}
	}
	public static void close(ResultSet... rs){
		for(ResultSet r : rs) {
			try{
				if(r != null){ r.close(); }
			}catch(Exception e) {}
		}
	}
	/**
	 * sqlのDate型の文字列(yyyy-MM-dd)を文字列(yyyy/M/d)に変換するメソッドです
	 * @param sqlDate sqlから出力されるDateの文字列
	 * @return 問題がなければ(yyyy/M/d)の文字列
	 */
	public static String dateFormat(String sqlDate) {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format2 = new SimpleDateFormat("yyyy/M/d");
		format1.setLenient(false);
		try {
			Date date = format1.parse(sqlDate);
			return format2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate; // ここに来たらエラーです。
	}

}
