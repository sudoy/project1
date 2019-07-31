package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.abc.asms.forms.S0022Form;
import com.abc.asms.utils.DBUtils;

public class S0022Service { //売上詳細表示のサービス

	public S0022Form findSaleDetail(String saleId) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		S0022Form form = null;

		try{
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT s.sale_id,s.sale_date,a.name,c.category_name,s.trade_name,s.unit_price,s.sale_number,s.note"
					+ ",(SELECT t.rate FROM taxes t WHERE t.start_date <= s.sale_date AND t.category_id = s.category_id ORDER BY t.start_date desc LIMIT 1) AS rate "
					+ "FROM sales s "
					+ "LEFT JOIN accounts a ON s.account_id = a.account_id "
					+ "LEFT JOIN categories c ON s.category_id = c.category_id "
					+ "WHERE s.sale_id = ?";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, saleId);
			rs = ps.executeQuery();
			rs.next();

			//jspに渡すform用意
			String saleDate = rs.getString("s.sale_date");
			String name = rs.getString("a.name");
			String categoryName = rs.getString("c.category_name");
			String tradeName = rs.getString("s.trade_name");
			int unitPrice = rs.getInt("s.unit_price");
			int saleNumber = rs.getInt("s.sale_number");
			int rate = rs.getInt("rate");
			String note = rs.getString("s.note");

			form = new S0022Form(saleId, saleDate, name, categoryName, tradeName, unitPrice, saleNumber,rate, note);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return form;
	}
	public Map<Integer, String> getHistories(String saleId){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<Integer, String> histories = new HashMap<>();
		int count = 0;

		try{
			//データベース接続
			con = DBUtils.getConnection();
			//SQL
			sql = "SELECT history_id,updated_at FROM histories WHERE sale_id = ? ORDER BY updated_at";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, saleId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int historyId = rs.getInt("history_id");
				String updatedAt;
				if(count==0) {
					updatedAt = "最新";
				}else {
					updatedAt = "履歴" + count + "-" + rs.getString("updated_at").replaceAll("-", "/");
				}
				histories.put(historyId,updatedAt);
				count++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return histories;
	}
}
