package com.abc.asms.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

import com.abc.asms.forms.S0024Form;
import com.abc.asms.utils.DBUtils;

public class S0024Service {

	public String getName(String id) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String name = "";

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//sql
			sql = "SELECT name FROM accounts WHERE account_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			rs.next();

			name = rs.getString("name");

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, ps, rs);
		}
		return name;
	}

	public String getCategoryName(String id) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String categoryName = "";

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//sql
			sql = "SELECT category_name FROM categories WHERE category_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			rs.next();

			categoryName = rs.getString("category_name");

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(con, ps, rs);
		}
		return categoryName;
	}

	public int update(S0024Form form) {

		int cnt = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		try{
			//データベース接続
	        con = DBUtils.getConnection();

	        //sql
			sql = "UPDATE sales SET sale_date = ?, account_id = ?, category_id = ?, trade_name = ?, unit_price = ?, sale_number = ?, note = ? "
					+ "WHERE sale_id = ?";
			ps = con.prepareStatement(sql);

			//ポストデータの内容をセット
			ps.setString(1, form.getSaleDate());
			ps.setString(2, form.getAccountId());
			ps.setString(3, form.getCategoryId());
			ps.setString(4, form.getTradeName());
			ps.setString(5, form.getUnitPrice());
			ps.setString(6, form.getSaleNumber());
			//詳細が空欄の場合
			String note = form.getNote();
			if(note.equals("")) {
				note = null;
			}
			ps.setString(7, note);
			ps.setString(8, form.getSaleId());

			//UPDATE命令を実行
			cnt = ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps);
		}
		return cnt;

	}

	public Map<Integer,String> getAccountMap(){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<Integer,String> map = new TreeMap<>();
		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL…アカウント名とidのリスト
			sql = "SELECT account_id,name FROM accounts ORDER BY account_id";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			//Map作成
			while(rs.next()) {
				map.put(rs.getInt("account_id"), rs.getString("name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}
		return map;
	}

	public Map<Integer,String> getCategoryMap(String categoryId){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<Integer,String> map = new TreeMap<>();
		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL…カテゴリー名とidのリスト
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

	public StringBuilder setInput(S0024Form form) throws UnsupportedEncodingException {

		StringBuilder input = new StringBuilder();
		input.append("saleId=" + form.getSaleId());
		input.append("&saleDate=" + form.getSaleDate());
		input.append("&accountId=" + form.getAccountId());
		input.append("&categoryId=" + form.getCategoryId());
		input.append("&tradeName=" + form.getTradeName());
		input.append("&unitPrice=" + form.getUnitPrice());
		input.append("&saleNumber=" + form.getSaleNumber());
		input.append("&note=" + URLEncoder.encode(form.getNote(), "UTF-8"));
		input.append("&cancel");

		return input;
	}
}
