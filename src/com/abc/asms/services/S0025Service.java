package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import com.abc.asms.forms.S0025Form;
import com.abc.asms.utils.DBUtils;

public class S0025Service {

	public S0025Form findSaleDetail(String saleId) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		S0025Form form = null;

		try{
			//データベース接続
			con = DBUtils.getConnection();

			//SQL…売上登録情報取得
			sql = "SELECT s.sale_id,s.sale_date,a.name,s.category_id,c.category_name,s.trade_name,s.unit_price,s.sale_number,(s.unit_price * s.sale_number) as subtotal,s.note "
					+ "FROM sales s "
					+ "LEFT JOIN accounts a ON s.account_id = a.account_id "
					+ "LEFT JOIN categories c ON s.category_id = c.category_id "
					+ "WHERE sale_id = ?";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, saleId);
			rs = ps.executeQuery();
			rs.next();

			//jspに渡すform用意、値をセット
			form = new S0025Form();
			form.setSaleId(rs.getString("s.sale_id"));
			form.setSaleDate(rs.getString("s.sale_date"));
			form.setName(rs.getString("a.name"));
			form.setCategoryId(rs.getString("s.category_id"));
			form.setCategoryName(rs.getString("c.category_name"));
			form.setTradeName(rs.getString("s.trade_name"));
			form.setUnitPrice(rs.getString("s.unit_price"));
			form.setSaleNumber(rs.getString("s.sale_number"));
			form.setSubtotal(rs.getString("subtotal"));
			form.setNote(rs.getString("s.note"));

			//カテゴリー名とidのリスト
			Map<Integer,String> categoryMap = DBUtils.getCategoryMap(form.getCategoryId());
			form.setCategoryMap(categoryMap);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return form;
	}

	public int delete(String saleId) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		int cnt = 0;

		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "DELETE FROM sales WHERE sale_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, saleId);
			cnt = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return cnt;
	}

}
