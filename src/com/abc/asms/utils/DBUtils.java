package com.abc.asms.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import com.abc.asms.forms.EntrySaleForm;

public class DBUtils {

	//DB接続
	public static Connection getConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("project1");
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


	/**
	 * GETでデータをjspに送るためのメソッドです。
	 * 売上登録、登録確認で使用します。
	 * @param  EntrySaleForm form
	 * @return StringBuilder
	 * @throws IOException
	 */

	public static StringBuilder sendData(EntrySaleForm form) throws IOException {

		StringBuilder sendData = new StringBuilder();
		sendData.append("saleDate=" + form.getSaleDate());
		sendData.append("&accountId=" + form.getAccountId());
		sendData.append("&categoryId=" + form.getCategoryId());
		sendData.append("&tradeName=" + URLEncoder.encode(form.getTradeName(),"UTF-8"));
		sendData.append("&unitPrice=" + form.getUnitPrice());
		sendData.append("&saleNumber=" + form.getSaleNumber());
		sendData.append("&note=" +  URLEncoder.encode(form.getNote(),"UTF-8"));

		return sendData;

	}


	/**
	 * Categoryのリストを抽出するメソッドです。
	 * 売上登録、登録確認で使用します。
	 * @return category_id,category_name,active_flgのList
	 * @throws ServletException
	 */
	public static List<EntrySaleForm> findCategory() throws ServletException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<EntrySaleForm> categoryList = new ArrayList<>();

		try{

			//DBと接続する
			con = DBUtils.getConnection();
			sql = "select category_id,category_name,active_flg from categories where active_flg = '1' ORDER BY category_id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			//DBの値の取り出し
			while(rs.next()) {
				String categoryId = rs.getString("category_id");
				String categoryName = rs.getString("category_name");
				String activeFlg = rs.getString("active_flg");


				EntrySaleForm form = new EntrySaleForm(categoryId,categoryName,activeFlg);
				categoryList.add(form);
			}


			//値をServletに送信
			return  categoryList;

		}catch(Exception e){
			throw new ServletException(e);

		}finally{

			try{
				DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}

	/**
	 * accountのリストを抽出します。
	 * 売上登録、登録確認で使用します。
	 * @return account_id,nameのList
	 * @throws ServletException
	 */
	public static List<EntrySaleForm> findAccount() throws ServletException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<EntrySaleForm> accountList = new ArrayList<>();

		try{

			//DBと接続する
			con = DBUtils.getConnection();
			sql = "select account_id,name from accounts ORDER BY account_id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			//DBの値の取り出し
			while(rs.next()) {
				String accountId = rs.getString("account_id");
				String name = rs.getString("name");

				EntrySaleForm form = new EntrySaleForm(accountId,name);
				accountList.add(form);

			}

			//値をServletに送信
			return  accountList;

		}catch(Exception e){
			throw new ServletException(e);

		}finally{

			try{
				DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}

	/**
	 * 引数のidに対応するカテゴリーと、有効フラグ1のカテゴリーを抽出するメソッド
	 * 売上編集、確認、削除で使用しています。
	 * @param categoryId
	 * @return カテゴリーidとカテゴリー名のMap
	 */
	public static Map<Integer,String> getCategoryMap(String categoryId){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<Integer,String> map = new TreeMap<>();
		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT category_id,category_name FROM categories WHERE active_flg = 1 OR category_id = ? ORDER BY category_id";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, categoryId);
			rs = ps.executeQuery();
			//Map作成
			while(rs.next()) {
				map.put(rs.getInt("category_id"), rs.getString("category_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}
		return map;
	}

	/**
	 * 入力値がSQL内のデータと一致すればtrue,違えばfalse
	 * @param mail 入力mail
	 * @return boolean値
	 * @throws ServletException
	 */
	public static boolean checkMailDB(String mail) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		boolean data = false;
		try {
			con = DBUtils.getConnection();

			// select文
			sql = "SELECT account_id FROM accounts WHERE mail = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, mail);
			rs = ps.executeQuery();
			data = rs.next();

		} catch (Exception e) {

		} finally {
			DBUtils.close(con, ps, rs);
		}
		return data;
	}
	/**
	 * S11、S22、S24、S25で使用
	 * 税率を取得するメソッド
	 * @param saleDate 販売日
	 * @param categoryId 商品カテゴリー
	 * @return String の税率
	 */
	public static String getRate(String saleDate,String categoryId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		String rate = "0";
		try {
			con = DBUtils.getConnection();

			// select文
			sql = "SELECT rate FROM taxes "
					+ "WHERE start_date <= ? AND category_id = ? "
					+ "ORDER BY start_date desc LIMIT 1";

			ps = con.prepareStatement(sql);
			ps.setString(1, saleDate);
			ps.setString(2, categoryId);
			rs = ps.executeQuery();
			rs.next();
			rate = rs.getString("rate");
		} catch (Exception e) {

		} finally {
			DBUtils.close(con, ps, rs);
		}
		return rate;
	}
}
