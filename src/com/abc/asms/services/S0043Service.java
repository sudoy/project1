package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.abc.asms.forms.AccountEditForm;
import com.abc.asms.utils.DBUtils;

public class S0043Service {

	public int update(AccountEditForm form) {

		int cnt = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		try{
			//データベース接続
	        con = DBUtils.getConnection();

	        //権限を数字に変換
	        String Authority = "0";
	        if(form.getAccountAuthority().equals("yes")) {
	        	Authority = form.getSalesAuthority().equals("yes") ? "11" : "10";
	        }else if(form.getAccountAuthority().equals("no")) {
	        	Authority = form.getSalesAuthority().equals("yes") ? "1" : "0";
	        }

	        //sql
	        if(form.getInputPass() == null || form.getInputPass().isEmpty()) { //パスワード入力無い場合
	        	sql = "UPDATE accounts SET name = ?, mail = ?, authority = ? WHERE account_id = ?";
	        	ps = con.prepareStatement(sql);
	        	ps.setString(1, form.getName());
	        	ps.setString(2, form.getMail());
	        	ps.setString(3, Authority);
	        	ps.setString(4, form.getAccountId());
	        }else {
	        	sql = "UPDATE accounts SET name = ?, mail = ?, password = MD5(?), authority = ? WHERE account_id = ?";
	        	ps = con.prepareStatement(sql);
	        	ps.setString(1, form.getName());
	        	ps.setString(2, form.getMail());
	        	ps.setString(3, form.getInputPass());
	        	ps.setString(4, Authority);
	        	ps.setString(5, form.getAccountId());
	        }

			//UPDATE命令を実行
			cnt = ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps);
		}
		return cnt;
	}

}
