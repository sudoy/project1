package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

			//update.jspに渡すform用意、値をセット
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
			Map<String,String> accountList = getMap("account");
			form.setAccountList(accountList);

			//SQL…カテゴリー名とidのリスト
			Map<String,String> categoryList = getMap("category");
			form.setCategoryList(categoryList);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return form;
	}

	public Map<String,String> getMap(String mapName){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<String,String> map = new HashMap<>();
		try {

			//データベース接続
			con = DBUtils.getConnection();

			if(mapName.equals("account")) {
				//SQL…アカウント名とidのリスト
				sql = "SELECT account_id,name FROM accounts";
				//SELECT命令の準備・実行
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				//Map作成
				while(rs.next()) {
					map.put(rs.getString("account_id"), rs.getString("name"));
				}
			}else if(mapName.equals("category")) {
				//SQL…カテゴリー名とidのリスト
				sql = "SELECT category_id,category_name FROM categories";
				//SELECT命令の準備・実行
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				//Map作成
				while(rs.next()) {
					map.put(rs.getString("category_id"), rs.getString("category_name"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}
		return map;
	}

	public List<String> validation(S0023Form form){

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
