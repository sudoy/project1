package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.forms.AccountEditForm;
import com.abc.asms.utils.DBUtils;

public class S0042Service {

	public AccountEditForm findAccount(String accountId) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		AccountEditForm form = null;

		try{
			//データベース接続
			con = DBUtils.getConnection();

			//SQL…アカウント情報取得
			sql = "SELECT account_id,name,mail,authority,version FROM accounts WHERE account_id = ?";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, accountId);
			rs = ps.executeQuery();
			rs.next();

			//jspに渡すform用意、値をセット
			form = new AccountEditForm();
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
			form.setVersion(rs.getInt("version"));

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return form;
	}

	public boolean checkMail(String mail, String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		boolean data = false;
		try {
			con = DBUtils.getConnection();

			// select文
			sql = "SELECT account_id FROM accounts WHERE mail = ? AND NOT account_id = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, mail);
			ps.setString(2, id);
			rs = ps.executeQuery();
			data = rs.next();

		} catch (Exception e) {

		} finally {
			DBUtils.close(con, ps, rs);
		}
		return data;
	}

}
