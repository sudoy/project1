package com.abc.asms.services;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.forms.S0011Form;
import com.abc.asms.utils.DBUtils;



public class S0011Service {

	public String calc(String unitPrice , String saleNumber) {

		int price = Integer.parseInt(unitPrice);
		int number = Integer.parseInt(saleNumber);

		int result = price * number ;
		String subtotal = String.valueOf(result);

		return subtotal;
	}

	public int insert(S0011Form form) throws ServletException {

		//EntryServletで使用
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		int id = 0 ;

		try{
			//DBへinsert
			con = DBUtils.getConnection();
			sql = "INSERT INTO sales (sale_date,account_id,category_id,trade_name,unit_price,sale_number,note)"+
					" VALUES (?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, form.getSaleDate());
			ps.setString(2, form.getAccountId());
			ps.setString(3, form.getCategoryId());
			ps.setString(4, form.getTradeName());
			ps.setString(5, form.getUnitPrice());
			ps.setString(6, form.getSaleNumber());
			ps.setString(7, form.getNote());
			ps.executeUpdate();

			//insertした直後にIDを取得
			rs = con.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
			rs.next();
			id = rs.getInt("LAST_INSERT_ID()");

		}catch(Exception e){
			throw new ServletException(e);

		}finally{

			try{
				DBUtils.close(con, ps, rs);
			}catch(Exception e){}
		}
		return id;
	}

	public List<S0011Form> findCategory() throws ServletException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<S0011Form> categoryList = new ArrayList<>();

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


				S0011Form form = new S0011Form(categoryId,categoryName,activeFlg);
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

	public List<S0011Form> findAccount() throws ServletException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<S0011Form> accountList = new ArrayList<>();

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

				S0011Form form = new S0011Form(accountId,name);
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

	public StringBuilder cancelData(S0011Form form) throws IOException {

		StringBuilder senddata = new StringBuilder();
		senddata.append("saleDate=" + form.getSaleDate());
		senddata.append("&accountId=" + form.getAccountId());
		senddata.append("&categoryId=" + form.getCategoryId());
		senddata.append("&tradeName=" + URLEncoder.encode(form.getTradeName(),"UTF-8"));
		senddata.append("&unitPrice=" + form.getUnitPrice());
		senddata.append("&saleNumber=" + form.getSaleNumber());
		senddata.append("&note=" +  URLEncoder.encode(form.getNote(),"UTF-8"));

		return senddata;

	}

}
