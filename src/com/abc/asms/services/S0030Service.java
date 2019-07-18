package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.utils.DBUtils;

public class S0030Service {

	//メールアドレスが既に登録されていればtureが返る
	public boolean mailCheck(String mail) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		boolean duplication = false;

		try{
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT mail FROM accounts WHERE mail = ?";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, mail);
			rs = ps.executeQuery();
			duplication = rs.next();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return duplication;
	}

}
