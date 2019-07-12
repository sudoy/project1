package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import com.abc.asms.forms.EntrySaleDataForm;
import com.abc.asms.utils.DBUtils;



public class S0011Service {

	public String calc(String unitPrice , String saleNumber) {

		int price = Integer.parseInt(unitPrice);
		int number = Integer.parseInt(saleNumber);

		int result = price * number ;
		String subtotal = String.valueOf(result);

		return subtotal;
	}

	public int insert(EntrySaleDataForm form) throws ServletException {

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

}
