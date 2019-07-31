package com.abc.asms.services;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;

import com.abc.asms.forms.S0023Form;
import com.abc.asms.utils.DBUtils;

public class S0023Service {

	public S0023Form findSaleDetail(String saleId) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		S0023Form form = null;

		try{
			//データベース接続
			con = DBUtils.getConnection();

			//SQL…売上登録情報取得
			sql = "SELECT sale_id,sale_date,account_id,category_id,trade_name,unit_price,sale_number,note,version "
					+ "FROM sales "
					+ "WHERE sale_id = ?";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, saleId);
			rs = ps.executeQuery();
			rs.next();

			//jspに渡すform用意、値をセット
			form = new S0023Form();
			form.setSaleId(rs.getString("sale_id"));
			form.setSaleDate(rs.getString("sale_date"));
			form.setAccountId(rs.getString("account_id"));
			form.setCategoryId(rs.getString("category_id"));
			form.setTradeName(rs.getString("trade_name"));
			form.setUnitPrice(rs.getString("unit_price"));
			form.setSaleNumber(rs.getString("sale_number"));
			form.setNote(rs.getString("note"));
			form.setVersion(rs.getInt("version"));

			//SQL…アカウント名とidのリスト
			Map<Integer,String> accountMap = getAccountMap();
			form.setAccountMap(accountMap);

			//SQL…カテゴリー名とidのリスト
			Map<Integer,String> categoryMap = DBUtils.getCategoryMap(form.getCategoryId());
			form.setCategoryMap(categoryMap);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return form;
	}

	public Map<Integer,String> getAccountMap(){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<Integer,String> map = new TreeMap<>();
		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL…アカウント名とidのリスト
			sql = "SELECT account_id,name FROM accounts ORDER BY account_id";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			//Map作成
			while(rs.next()) {
				map.put(rs.getInt("account_id"), rs.getString("name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}
		return map;
	}

	public StringBuilder setInput(S0023Form form) throws IOException {

		StringBuilder input = new StringBuilder();
		input.append("saleId=" + form.getSaleId());
		input.append("&saleDate=" + form.getSaleDate());
		input.append("&accountId=" + form.getAccountId());
		input.append("&categoryId=" + form.getCategoryId());
		input.append("&tradeName=" + URLEncoder.encode(form.getTradeName(), "UTF-8"));
		input.append("&unitPrice=" + form.getUnitPrice());
		input.append("&saleNumber=" + form.getSaleNumber());
		input.append("&note=" + URLEncoder.encode(form.getNote(), "UTF-8"));
		input.append("&version=" + form.getVersion());

		return input;
	}

}
