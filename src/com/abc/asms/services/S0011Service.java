package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

	public void insert(EntrySaleDataForm form) throws ServletException {

		//EntryServletで使用
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

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

			System.out.println(ps);

			ps.executeUpdate();

		}catch(Exception e){
			throw new ServletException(e);

		}finally{

			try{
				DBUtils.close(con, ps);
			}catch(Exception e){}
		}
	}


}
