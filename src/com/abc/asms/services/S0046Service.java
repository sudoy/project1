package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.abc.asms.utils.DBUtils;

public class S0046Service extends HttpServlet {
	/**
	 * 入力値がSQL内のデータと一致すればtrue,違えばfalse
	 * @param mail 入力mail
	 * @return boolean値
	 * @throws ServletException
	 */
	public boolean checkDB(String mail) throws ServletException {
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
			while (rs.next()) {
				data = true;
			}

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return data;
	}
}
