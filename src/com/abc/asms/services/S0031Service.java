package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.forms.EntryAccountForm;
import com.abc.asms.utils.DBUtils;

public class S0031Service {

	public int insert(EntryAccountForm form) {

		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		String sql = null;
		int accountId = 0 ;

		try{

			//データベース接続
			con = DBUtils.getConnection();

			//sql
			sql = "INSERT INTO accounts (name,mail,password,authority) VALUES (?,?,MD5(?),?)";
			ps1 = con.prepareStatement(sql);

			//権限を数字に変換
	        String authority = "0";
	        if(form.getAccountAuthority().equals("yes")) {
	        	authority = form.getSalesAuthority().equals("yes") ? "11" : "10";
	        }else if(form.getAccountAuthority().equals("no")) {
	        	authority = form.getSalesAuthority().equals("yes") ? "1" : "0";
	        }

			ps1.setString(1, form.getName());
			ps1.setString(2, form.getMail());
			ps1.setString(3, form.getPassword());
			ps1.setString(4, authority);
			ps1.executeUpdate();

			//id取得
			sql = "SELECT account_id FROM accounts WHERE name = ? AND mail = ?";
			ps2 = con.prepareStatement(sql);
			ps2.setString(1, form.getName());
			ps2.setString(2, form.getMail());
			rs = ps2.executeQuery();
			rs.next();
			accountId = rs.getInt("account_id");

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps1, rs);
			DBUtils.close(ps2);
		}

		return accountId;
	}
}
