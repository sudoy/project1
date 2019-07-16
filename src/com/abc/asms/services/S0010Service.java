package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.forms.EntrySaleDataForm;
import com.abc.asms.utils.DBUtils;

public class S0010Service {

	public List<EntrySaleDataForm> findCategory() throws ServletException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<EntrySaleDataForm> categoryList = new ArrayList<>();

		try{

			//DBと接続する
			con = DBUtils.getConnection();
			sql = "select category_id,category_name,active_flg from categories where active_flg = '1'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			//DBの値の取り出し
			while(rs.next()) {
				String categoryId = rs.getString("category_id");
				String categoryName = rs.getString("category_name");
				String activeFlg = rs.getString("active_flg");


				EntrySaleDataForm form = new EntrySaleDataForm(categoryId,categoryName,activeFlg);
				categoryList.add(form);
			}


			//値をServletに送信
			return  categoryList;

		}catch(Exception e){
			throw new ServletException(e);

		}finally{

			try{
				com.abc.asms.utils.DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}

	public List<EntrySaleDataForm> findAccount() throws ServletException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<EntrySaleDataForm> accountList = new ArrayList<>();

		try{

			//DBと接続する
			con = DBUtils.getConnection();
			sql = "select account_id,name from accounts";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			//DBの値の取り出し
			while(rs.next()) {
				String accountId = rs.getString("account_id");
				String name = rs.getString("name");

				EntrySaleDataForm form = new EntrySaleDataForm(accountId,name);
				accountList.add(form);

			}

			//値をServletに送信
			return  accountList;

		}catch(Exception e){
			throw new ServletException(e);

		}finally{

			try{
				com.abc.asms.utils.DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}

	public String accountCheck(EntrySaleDataForm form){

		Connection con = null;
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String error = "";

		try {
			//データベース接続
			con = DBUtils.getConnection();

			sql = "SELECT count(account_id) as cnt FROM accounts WHERE account_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, form.getAccountId());
			rs = ps.executeQuery();

			//アカウントの関連チェック
			rs.next();
			if(rs.getInt("cnt") != 1) {
				error = "アカウントテーブルに存在しません。";
			}

		} catch (Exception e) {

		}finally{
			DBUtils.close(con, ps, rs);
		}

		return error;
	}

	public String categoryCheck(EntrySaleDataForm form){

		Connection con = null;
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String error = "";

		try {


			con = DBUtils.getConnection();

			sql = "SELECT count(category_id) as cnt FROM categories WHERE category_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, form.getCategoryId());
			rs = ps.executeQuery();

			//テーブル存在チェック
			rs.next();
			if(rs.getInt("cnt") != 1) {
				error = "商品カテゴリーテーブルに存在しません。";
			}

		} catch (Exception e) {

		}finally{
			DBUtils.close(con, ps, rs);
		}

		return error;
	}
}
