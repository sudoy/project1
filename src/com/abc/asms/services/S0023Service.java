package com.abc.asms.services;

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
			sql = "SELECT sale_id,sale_date,account_id,category_id,trade_name,unit_price,sale_number,note "
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

			//SQL…アカウント名とidのリスト
			Map<Integer,String> accountMap = getAccountMap();
			form.setAccountMap(accountMap);

			//SQL…カテゴリー名とidのリスト
			Map<Integer,String> categoryMap = getCategoryMap(form.getCategoryId());
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

	public Map<Integer,String> getCategoryMap(String categoryId){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<Integer,String> map = new TreeMap<>();
		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL…カテゴリー名とidのリスト
			sql = "SELECT category_id,category_name FROM categories WHERE active_flg = 1 OR category_id = ? ORDER BY category_id";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, categoryId);
			rs = ps.executeQuery();
			//Map作成
			while(rs.next()) {
				map.put(rs.getInt("category_id"), rs.getString("category_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}
		return map;
	}


	public int countAccount(String AccountId){

		int cnt = 0;
		Connection con = null;
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//担当テーブル存在チェック
			sql = "SELECT count(account_id) as cnt FROM accounts WHERE account_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, AccountId);
			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return cnt;
	}

	public int countCategory(String categoryId){

		int cnt = 0;
		Connection con = null;
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//カテゴリーテーブル存在チェック
			sql = "SELECT count(category_id) as cnt FROM categories WHERE category_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, categoryId);
			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return cnt;
	}

}
