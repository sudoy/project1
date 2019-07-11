package com.abc.asms.services;

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

	public void update(S0024Form form) {

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
			ps.setInt(5, form.getUnitPrice());
			ps.setInt(6, form.getSaleNumber());
			//詳細が空欄の場合
			String note = form.getNote();
			if(note.equals("")) {
				note = null;
			}
			ps.setString(7, note);
			ps.setString(8, form.getSaleId());

			//UPDATE命令を実行
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps);
		}

	}
}
