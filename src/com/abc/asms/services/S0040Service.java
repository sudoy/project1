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
		String sql = null;
		ResultSet rs = null;
		int length = 0;
		String name = acf.getName();
		String mail = acf.getMail();
		String authority = acf.getAuthority();
		List<Object> holder = new ArrayList<>();
		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT count(account_id) FROM accounts where 1=1 ";

			if (name != null && !name.equals("")) {
				sql += "AND name LIKE ? ";
				holder.add("%" + name + "%");
			}
			if (mail != null && !mail.equals("")) {
				sql += "AND mail = ? ";
				holder.add(mail);
			}
			sql += "AND authority REGEXP ? ";
			holder.add(authority);

			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
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

	public String setAuthority(String salesAuthority, String accountAuthority) {
		String Authority = "^";
		if (salesAuthority == null) {
			salesAuthority = "";
		}
		if (accountAuthority == null) {
			accountAuthority = "";
		}
		switch (accountAuthority) {
		case "yes":
			Authority += "1";
			break;
		case "no":
			break;
		default:
			Authority += "1?";
			break;
		}
		switch (salesAuthority) {
		case "yes":
			Authority += "1";
			break;
		case "no":
			Authority += "0";
			break;
		default:
			Authority += "(1|0)";
			break;
		}
		return Authority + "$";
	}
}
