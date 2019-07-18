package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.abc.asms.forms.SaleConditionalForm;
import com.abc.asms.utils.DBUtils;

public class S0020Service {

	public Map<Integer, String> getMap(String idName, String getName, String tableName) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT " + idName + "," + getName + " FROM " + tableName + " ORDER BY ?";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, idName);
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(idName);
				String name = rs.getString(getName);
				map.put(id, name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}

		return map;
	}

	public int checkListSize(SaleConditionalForm scf) {
		Connection con = null;
		PreparedStatement ps = null;
		StringBuilder sql = new StringBuilder();
		ResultSet rs = null;
		int length = 0;
		String[] date = scf.getDate();
		String accountId = scf.getAccountId();
		String[] categoryId = scf.getCategoryId();
		String tradeName = scf.getTradeName();
		String note = scf.getNote();
		List<Object> holder = new ArrayList<>();
		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql.append("SELECT count(sale_id) FROM sales where 1=1 ");

			if (!date[0].equals("")) {
				sql.append("AND sale_date >= ? ");
				holder.add(date[0]);
			}
			if (!date[1].equals("")) {
				sql.append("AND sale_date <= ? ");
				holder.add(date[1]);
			}
			if (!accountId.equals("")) {
				sql.append("AND account_id = ? ");
				holder.add(accountId);
			}
			if (categoryId != null) {
				sql.append("AND category_id in('false'");
				for (String cId : categoryId) {
					sql.append(",?");
					holder.add(cId);
				}
				sql.append(") ");
			}
			if (tradeName != null && !tradeName.equals("")) {
				sql.append("AND trade_name like ? ");
				holder.add("%" + tradeName + "%");
			}
			if (note != null && !note.equals("")) {
				sql.append("AND note like ? ");
				holder.add("%" + note + "%");
			}

			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql.toString());
			for (int i = 0; i < holder.size(); i++) {
				ps.setObject(i + 1, holder.get(i));
			}
			rs = ps.executeQuery();

			rs.next();
			length = rs.getInt("count(sale_id)");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return length;
	}

}
