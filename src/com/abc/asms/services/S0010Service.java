package com.abc.asms.services;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			sql = "select category_id,category_name,active_flg from categories where active_flg = '1' ORDER BY category_id";
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
				DBUtils.close(con, ps, rs);
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
			sql = "select account_id,name from accounts ORDER BY account_id";
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
				DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}

	public StringBuilder sendData(S0010Form form) throws IOException {

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
