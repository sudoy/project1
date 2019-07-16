package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.abc.asms.forms.AccountConditionalForm;
import com.abc.asms.forms.S0041Form;
import com.abc.asms.utils.DBUtils;

public class S0041Service {

	public List<S0041Form> getDB(AccountConditionalForm acf) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = acf.getName();
		String mail = acf.getMail();
		String accountAuthority = acf.getAccountAuthority();
		String salesAuthority = acf.getSalesAuthority();
		List<Object> holder = new ArrayList<>();
		List<S0041Form> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql.append("SELECT account_id,name,mail,authority FROM accounts where 1=1 ");

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
			sql.append("ORDER BY account_id");

			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql.toString());
			for (int i = 0; i < holder.size(); i++) {
				ps.setObject(i + 1, holder.get(i));
			}
			rs = ps.executeQuery();

			while (rs.next()) {
				String account_id = rs.getString("account_id");
				String getName = rs.getString("name");
				String getMail = rs.getString("mail");
				String getAuthority = DBUtils.setAuthority(rs.getString("authority"));
				list.add(new S0041Form(account_id, getName, getMail, getAuthority));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}

		return list;
	}
}
