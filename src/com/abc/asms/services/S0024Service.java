package com.abc.asms.services;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			sql = "UPDATE sales SET sale_date = ?, account_id = ?, category_id = ?, trade_name = ?, unit_price = ?, sale_number = ?, note = ?, version = ? "
					+ "WHERE sale_id = ? AND version = ?";
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
			ps.setInt(8, form.getVersion() + 1);
			ps.setString(9, form.getSaleId());
			ps.setInt(10, form.getVersion());

			//UPDATE命令を実行
			cnt = ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps);
		}
		return cnt;

	}

	public StringBuilder setInput(S0024Form form) throws IOException {

		StringBuilder input = new StringBuilder();
		input.append("saleId=" + form.getSaleId());
		input.append("&saleDate=" + form.getSaleDate());
		input.append("&accountId=" + form.getAccountId());
		input.append("&categoryId=" + form.getCategoryId());
		if(form.getTradeName() != null) {
			input.append("&tradeName=" + URLEncoder.encode(form.getTradeName(), "UTF-8"));
		}else {
			input.append("&tradeName=" + form.getTradeName());
		}
		input.append("&unitPrice=" + form.getUnitPrice());
		input.append("&saleNumber=" + form.getSaleNumber());
		if(form.getNote() != null) {
			input.append("&note=" + URLEncoder.encode(form.getNote(), "UTF-8"));
		}else {
			input.append("&note=" + form.getNote());
		}
		input.append("&version=" + form.getVersion());
		input.append("&cancel");

		return input;
	}
}
