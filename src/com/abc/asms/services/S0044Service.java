package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.utils.DBUtils;

public class S0044Service extends S0042Service {

	public int delete(String accountId, int version) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		int cnt = 0;

		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "DELETE FROM accounts WHERE account_id = ? AND version = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, accountId);
			ps.setInt(2, version);
			cnt = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return cnt;
	}

}
