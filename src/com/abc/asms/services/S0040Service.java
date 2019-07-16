package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.abc.asms.forms.AccountConditionalForm;
import com.abc.asms.utils.DBUtils;

public class S0040Service {
	public int checkListSize(AccountConditionalForm acf) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int length = 0;
		String name = acf.getName();
		String mail = acf.getMail();
		String accountAuthority = acf.getAccountAuthority();
		String salesAuthority = acf.getSalesAuthority();
		List<Object> holder = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql.append("SELECT count(account_id) FROM accounts where 1=1 ");

			if (!name.equals("")) {
				sql.append("AND name LIKE ? ");
				holder.add("%" + name + "%");
			}
			if (!mail.equals("")) {
				sql.append("AND mail = ? ");
				holder.add(mail);
			}
			if(accountAuthority.equals("yes")) {
				sql.append("AND authority >= 10 ");
			}else if(accountAuthority.equals("no")) {
				sql.append("AND authority < 10 ");
			}
			if(salesAuthority.equals("yes")) {
				sql.append("AND authority % 2 = 1 ");
			}else if(salesAuthority.equals("no")) {
				sql.append("AND authority % 2 = 0 ");
			}


			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql.toString());
			for (int i = 0; i < holder.size(); i++) {
				ps.setObject(i + 1, holder.get(i));
			}
			rs = ps.executeQuery();

			rs.next();
			length = rs.getInt("count(account_id)");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return length;
	}

}
