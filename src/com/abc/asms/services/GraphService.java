package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import com.abc.asms.forms.GraphForm;
import com.abc.asms.utils.DBUtils;

public class GraphService {

	public GraphForm getGraphData(int thisYear, GraphForm form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String calc = DBUtils.setRounding();

		try {
			//DB接続
			con = DBUtils.getConnection();

			//SQL作成、実行
			sql = "SELECT DATE_FORMAT(sale_date, '%Y-%m') AS sale_date, SUM("
					+ calc
					+ "(unit_price * sale_number * "
					+ "(1+(SELECT t.rate FROM taxes t WHERE t.start_date <= s.sale_date AND t.category_id = s.category_id ORDER BY t.start_date desc LIMIT 1)/100)"
					+ ")) AS sum "
					+ "FROM sales s "
					+ "WHERE sale_date BETWEEN ? AND ? "
					+ "GROUP BY DATE_FORMAT(sale_date, '%Y%m')";
			ps = con.prepareStatement(sql);
			ps.setString(1, (thisYear - 1) + "/1/1");
			ps.setString(2, thisYear + "/12/31");
			rs = ps.executeQuery();

			//月別売上リストと最大値取得
			long[] thisYearlist = new long[12];
			long[] lastYearlist = new long[12];
			long maxSale = 0;
			while(rs.next()) {
				//今年の売上
				if(rs.getString("sale_date").contains(thisYear + "-")) {
					int index = Integer.valueOf(rs.getString("sale_date").substring(rs.getString("sale_date").indexOf("-") + 1)) - 1;
					thisYearlist[index] = rs.getLong("sum");
				}
				//前年の売上
				if(rs.getString("sale_date").contains((thisYear - 1) + "-")) {
					int index = Integer.valueOf(rs.getString("sale_date").substring(rs.getString("sale_date").indexOf("-") + 1)) - 1;
					lastYearlist[index] = rs.getLong("sum");
				}
				//最大値
				maxSale = maxSale <= rs.getLong("sum") ? rs.getLong("sum") : maxSale;
			}

//			DecimalFormat df = new DecimalFormat("#.#");
//			for(int i = 0; i < thisYearlist.length; i++) {
//				thisYearlist[i] = Long.valueOf(df.format((double)thisYearlist[i] / 10000));
//			}
//			for(int i = 0; i < lastYearlist.length; i++) {
//				lastYearlist[i] = Long.valueOf(df.format((double)lastYearlist[i] / 10000));
//			}

			//formにセット
			form.setThisYearList(createDataOfjs(thisYearlist));
			form.setLastYearList(createDataOfjs(lastYearlist));
			form.setMaxSale((long) Math.ceil((double) maxSale / 10000));

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}
		return form;
	}

	public String[] createDataOfjs(long[] list) {

		String[] dataList = new String[12];
		DecimalFormat df = new DecimalFormat("#.#");
		for(int i = 0; i < list.length; i++) {
			dataList[i] = df.format((double)list[i] / 10000);
		}
		return dataList;
	}
}
