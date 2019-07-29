package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.forms.S0021Form;
import com.abc.asms.forms.SaleConditionalForm;
import com.abc.asms.utils.DBUtils;

public class S0021Service {

	public List<S0021Form> getDB(SaleConditionalForm scf) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] date = scf.getDate();
		String accountId = scf.getAccountId();
		String[] categoryId = scf.getCategoryId();
		String tradeName = scf.getTradeName();
		String note = scf.getNote();
		List<Object> holder = new ArrayList<>();
		List<S0021Form> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql.append("SELECT s.sale_id,s.sale_date");
			sql.append(",(SELECT a.name FROM accounts a WHERE s.account_id = a.account_id) AS name");
			sql.append(",(SELECT c.category_name FROM categories c WHERE s.category_id = c.category_id) AS category_name");
			sql.append(",s.trade_name,s.unit_price,s.sale_number ");
			sql.append(",(SELECT t.rate FROM taxes t WHERE t.start_date <= s.sale_date AND t.category_id = s.category_id ORDER BY t.start_date desc LIMIT 1) AS rate ");
			sql.append("FROM sales s WHERE 1=1 ");

			if (!date[0].equals("")) {
				sql.append("AND s.sale_date >= ? ");
				holder.add(date[0]);
			}
			if (!date[1].equals("")) {
				sql.append("AND s.sale_date <= ? ");
				holder.add(date[1]);
			}
			if (!accountId.equals("")) {
				sql.append("AND s.account_id = ? ");
				holder.add(accountId);
			}
			if (categoryId != null) {
				sql.append("AND s.category_id in('false'");
				for (String cId : categoryId) {
					sql.append(",?");
					holder.add(cId);
				}
				sql.append(") ");
			}
			if (!tradeName.equals("")) {
				sql.append("AND s.trade_name like ? ");
				holder.add("%" + tradeName + "%");
			}
			if (!note.equals("")) {
				sql.append("AND s.note like ? ");
				holder.add("%" + note + "%");
			}
			sql.append("ORDER BY s.sale_id");

			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql.toString());
			for (int i = 0; i < holder.size(); i++) {
				ps.setObject(i + 1, holder.get(i));
			}
			rs = ps.executeQuery();

			while (rs.next()) {
				int saleId = rs.getInt("s.sale_id");
				String getDate = DBUtils.dateFormat(rs.getString("s.sale_date"));
				String accountName = rs.getString("name");
				String categoryName = rs.getString("category_name");
				String getTtradeName = rs.getString("s.trade_name");
				int unitPrice = rs.getInt("s.unit_price");
				int saleNumber = rs.getInt("s.sale_number");
				int rate = rs.getInt("rate");
				list.add(new S0021Form(
						saleId, getDate, accountName, categoryName, getTtradeName, unitPrice,saleNumber,rate
						));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}

		return list;
	}
}
