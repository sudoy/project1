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

	/**
	 * authorityの値を対応する文字列に変換するメソッド
	 * @param authority [11,10,1,0]のいずれか
	 * @return HTMLに出力される文字
	 */
	public static String setAuthority(String authority) {
		switch (authority) {
		case "0":
			return "権限なし";
		case "1":
			return "売上登録";
		case "10":
			return "アカウント登録";
		case "11":
			return "売上登録/アカウント登録";
		default:
			return "エラー";
		}
	}

	/**
	 * 担当のテーブル存在チェック
	 * @param accountId
	 * @return アカウントテーブルに引数のidがあれば1、なければ0
	 */
	public static int countAccount(String accountId){

		int cnt = 0;
		Connection con = null;
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//担当テーブル存在チェック
			sql = "SELECT count(account_id) as cnt FROM accounts WHERE account_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, accountId);
			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return cnt;
	}

	/**
	 * カテゴリーのテーブル存在チェック
	 * @param AccountId
	 * @return カテゴリーテーブルに引数のidがあれば1、なければ0
	 */
	public static int countCategory(String categoryId){

		int cnt = 0;
		Connection con = null;
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//カテゴリーテーブル存在チェック
			sql = "SELECT count(category_id) as cnt FROM categories WHERE category_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, categoryId);
			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return cnt;
	}
}
