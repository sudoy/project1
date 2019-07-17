package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.forms.S0044Form;
import com.abc.asms.utils.DBUtils;

public class S0044Service {

	public S0044Form findAccount(String accountId) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		S0044Form form = null;

		try{
			//データベース接続
			con = DBUtils.getConnection();

			//SQL…アカウント情報取得
			sql = "SELECT account_id,name,mail,authority FROM accounts WHERE account_id = ?";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, accountId);
			rs = ps.executeQuery();
			rs.next();

			//jspに渡すform用意、値をセット
			form = new S0044Form();
			form.setAccountId(rs.getString("account_id"));
			form.setName(rs.getString("name"));
			form.setMail(rs.getString("mail"));
			//権限
			String authority = rs.getString("authority");
			if(authority.equals("1") || authority.equals("11")) {
				form.setSalesAuthority("yes");
			}else {
				form.setSalesAuthority("no");
			}
			if(authority.equals("10") || authority.equals("11")) {
				form.setAccountAuthority("yes");
			}else {
				form.setAccountAuthority("no");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return form;
	}

	public int delete(String accountId) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		int cnt = 0;

		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "DELETE FROM accounts WHERE account_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, accountId);
			cnt = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return cnt;
	}

}
