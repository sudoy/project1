package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.abc.asms.forms.S0046Form;
import com.abc.asms.utils.DBUtils;

public class S0046Service extends HttpServlet {

	public void updateDB(S0046Form S46F) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			con = DBUtils.getConnection();
			// select文
			sql = "UPDATE accounts SET password = MD5(?) WHERE mail = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, S46F.getPassword1());
			ps.setString(2, S46F.getMail());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps);
		}
		return ;
	}
}
