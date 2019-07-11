package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.forms.C0020Form;
import com.abc.asms.utils.HTMLUtils;

public class C0020Service {


	public List<C0020Form> find (int accountId ,LocalDate date) throws ServletException{

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		C0020Form form = new C0020Form();
		List<C0020Form> findList = new ArrayList<>();
		LocalDate start =  date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate finish = date.with(TemporalAdjusters.lastDayOfMonth());

		try{

			//DBと接続する
			con = com.abc.asms.utils.DBUtils.getConnection();
			sql = "SELECT sale_id,s.sale_date,c.category_name,s.trade_name,s.unit_price,s.sale_number," +
					"(s.unit_price * s.sale_number) as subtotal" +
					" FROM sales s JOIN categories c" +
					" ON s.category_id = c.category_id" +
					" WHERE account_id = ? and sale_date between ? and ?";

			ps = con.prepareStatement(sql);

			ps.setInt(1,accountId);
			ps.setObject(2,start);
			ps.setObject(3,finish);

			rs = ps.executeQuery();

			//DBの値の取り出し
			while(rs.next()){
				int saleId = rs.getInt("sale_id");
				String saleDate = HTMLUtils.dateFormat(rs.getString("sale_date"));
				String categoryName = rs.getString("category_name");
				String tradeName = rs.getString("trade_name");
				int unitPrice = rs.getInt("unit_price");
				int saleNumber = rs.getInt("sale_number");
				int subtotal = rs.getInt("subtotal");


				//DBの値をセットする
				form = new C0020Form(saleId,saleDate,categoryName,tradeName,unitPrice,saleNumber,subtotal);
				findList.add(form);

			}

			//値をServletに送信
			return findList;

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				com.abc.asms.utils.DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}

	public int findSale (int accountId, LocalDate date) throws ServletException{

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		LocalDate start =  date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate finish = date.with(TemporalAdjusters.lastDayOfMonth());

		try{

			//DBと接続する
			con = com.abc.asms.utils.DBUtils.getConnection();
			sql = "select sum((unit_price * sale_number)) as total from sales where account_id = ?"+
					" and sale_date between ? and ?";

			ps = con.prepareStatement(sql);

			ps.setInt(1,accountId);
			ps.setObject(2,start);
			ps.setObject(3,finish);

			rs = ps.executeQuery();

			//DBの値の取り出し
			rs.next();
			int total = rs.getInt("total");

			//値をServletに送信
			return total;

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				com.abc.asms.utils.DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}

	public double findAllsale (LocalDate date) throws ServletException{

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		LocalDate start =  date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate finish = date.with(TemporalAdjusters.lastDayOfMonth());

		try{

			//DBと接続する
			con = com.abc.asms.utils.DBUtils.getConnection();
			sql = "select sum((unit_price * sale_number)) as allsale from sales"+
					" where sale_date between ? and ?";

			ps = con.prepareStatement(sql);

			ps.setObject(1,start);
			ps.setObject(2,finish);

			rs = ps.executeQuery();

			//DBの値の取り出し
			rs.next();
			double allsale = rs.getDouble("allsale");

			//値をServletに送信
			return allsale;

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				com.abc.asms.utils.DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}


}
