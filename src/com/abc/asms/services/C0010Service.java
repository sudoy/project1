package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.abc.asms.forms.AccountForm;
import com.abc.asms.utils.DBUtils;

public class C0010Service extends HttpServlet {
	/**
	 * 入力値がSQL内のデータと一致すれば名前、違えば空文字を返す
	 * @param email 入力Email
	 * @param pass 入力Pass
	 * @return 名前or空文字
	 * @throws ServletException
	 */
	public AccountForm checkDB(String mail, String password) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		int accountId = -1;
		String name = "";
		int authority = -1;
		AccountForm account = new AccountForm(-1, null, 0);
		try {
			con = DBUtils.getConnection();

			// select文
			sql = "SELECT account_id,name,authority FROM accounts "
					+ "WHERE mail = ? AND password = MD5(?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, mail);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				accountId = rs.getInt("account_id");
				name = rs.getString("name");
				authority = rs.getInt("authority");
				account = new AccountForm(accountId, name, authority);
			}

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return account;
	}
}
