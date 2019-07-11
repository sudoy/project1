package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
