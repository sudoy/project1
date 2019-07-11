package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.forms.S0010Form;
import com.abc.asms.utils.DBUtils;

public class S0010Service {

	public List<S0010Form> findCategory() throws ServletException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<S0010Form> categoryList = new ArrayList<>();

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

				S0010Form form = new S0010Form(categoryId,categoryName,activeFlg);
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

	public List<S0010Form> findAccount() throws ServletException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<S0010Form> accountList = new ArrayList<>();

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

				S0010Form form = new S0010Form(accountId,name);
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

	public List<String> validation(S0010Form form){

		List<String> error = new ArrayList<>();

		Connection con = null;
		String sql = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//販売日必須入力、形式
			if(form.getSaleDate().isEmpty()) {
				error.add("販売日を入力して下さい。");
			}else {
				if(form.getSaleDate().matches("^[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}$")) {
					try {
						DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu/M/d").withResolverStyle(ResolverStyle.STRICT);
						LocalDate.parse(form.getSaleDate(),f);
					} catch (Exception e) {
						e.printStackTrace();
						error.add("入力した日付が不正です。");
					}
				}else {
					error.add("販売日を正しく入力して下さい。");
				}
			}


			//担当必須入力、テーブル存在チェック
			if(form.getAccountId() == null) {
				error.add("担当が未選択です。");
			}else {
				sql = "SELECT count(account_id) as cnt FROM accounts WHERE account_id = ?";
				ps1 = con.prepareStatement(sql);
				ps1.setString(1, form.getAccountId());
				rs1 = ps1.executeQuery();
				rs1.next();
				if(rs1.getInt("cnt") != 1) {
					error.add("アカウントテーブルに存在しません。");
				}
			}


			//カテゴリー必須入力、テーブル存在チェック
			if(form.getCategoryId() == null) {
				error.add("商品カテゴリーが未選択です。");
			}else {
				sql = "SELECT count(category_id) as cnt FROM categories WHERE category_id = ?";
				ps2 = con.prepareStatement(sql);
				ps2.setString(1, form.getCategoryId());
				rs2 = ps2.executeQuery();
				rs2.next();
				if(rs2.getInt("cnt") != 1) {
					error.add("商品カテゴリーテーブルに存在しません。");
				}
			}


			//商品名必須入力、長さ(バイト数)
			if(form.getTradeName().isEmpty()) {
				error.add("商品名を入力して下さい。");
			}else if(101 <= form.getTradeName().getBytes("UTF-8").length) {
				error.add("商品名が長すぎます。");
			}

			//単価必須入力、形式、長さ(バイト数)
			if(form.getUnitPrice().isEmpty()) {
				error.add("単価を入力して下さい。");
			}else if(!form.getUnitPrice().matches("^[1-9][0-9]*$")) {
				error.add("単価を正しく入力して下さい。");
			}else if(10 <= form.getUnitPrice().getBytes("UTF-8").length) {
				error.add("単価が長すぎます。");
			}

			//個数必須入力、形式、長さ(バイト数)
			if(form.getSaleNumber().isEmpty()) {
				error.add("個数を入力して下さい。");
			}else if(!form.getSaleNumber().matches("^[1-9][0-9]*$")) {
				error.add("個数を正しく入力して下さい。");
			}else if(10 <= form.getSaleNumber().getBytes("UTF-8").length) {
				error.add("個数が長すぎます。");
			}

			//備考長さ
			if(401 <= form.getNote().getBytes("UTF-8").length) {
				error.add("備考が長すぎます。");
			}

		} catch (Exception e) {
			e.printStackTrace();
			error.add("バリデーションエラー");
		}finally{
			DBUtils.close(con, ps1, rs1);
			DBUtils.close(ps2, rs2);
		}

		return error;
	}
}
