package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.forms.C0020Form;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.HTMLUtils;

public class C0020Service {


	public List<C0020Form> find (int accountId ,LocalDate date) throws ServletException{

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		C0020Form form = new C0020Form();
		List<C0020Form> findList = new ArrayList<>();

		//月初と月末の日付を取得
		LocalDate start =  date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate finish = date.with(TemporalAdjusters.lastDayOfMonth());

		try{

			//DBと接続する
			con = DBUtils.getConnection();
			sql = "SELECT s.sale_id,s.sale_date,c.category_name,s.trade_name,s.unit_price,s.sale_number," +
					"(s.unit_price * s.sale_number) as subtotal" +
					" FROM sales s JOIN categories c" +
					" ON s.category_id = c.category_id" +
					" WHERE account_id = ? and sale_date between ? and ?" +
					" ORDER BY s.sale_id";

			ps = con.prepareStatement(sql);

			//DBから個人の売り上げ情報を取得
			ps.setInt(1,accountId);
			ps.setObject(2,start);
			ps.setObject(3,finish);

			rs = ps.executeQuery();

			//DBの値の取り出し
			while(rs.next()) {
				int saleId = rs.getInt("sale_id");
				String saleDate = HTMLUtils.dateFormat(rs.getString("sale_date"));
				String categoryName = rs.getString("category_name");
				String tradeName = rs.getString("trade_name");
				int unitPrice = rs.getInt("unit_price");
				int saleNumber = rs.getInt("sale_number");
				long subTotal = rs.getLong("subtotal");

				//DBの値をセットする
				form = new C0020Form(saleId,saleDate,categoryName,tradeName,unitPrice,saleNumber,subTotal);
				findList.add(form);
			}

			//値をServletに送信
			return findList;

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}

	public long findSale (int accountId, LocalDate date) throws ServletException{

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		//月初と月末の日付を取得
		LocalDate start =  date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate finish = date.with(TemporalAdjusters.lastDayOfMonth());

		try{

			//DBと接続する
			con = DBUtils.getConnection();
			sql = "select sum((unit_price * sale_number)) as total from sales where account_id = ?"+
					" and sale_date between ? and ?";

			ps = con.prepareStatement(sql);

			//DBから1か月分の個人売り上げ合計を取得
			ps.setInt(1,accountId);
			ps.setObject(2,start);
			ps.setObject(3,finish);

			rs = ps.executeQuery();

			//DBの値の取り出し
			rs.next();
			long total = rs.getLong("total");

			//値をServletに送信
			return total;

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}

	public long findAllsale (LocalDate date) throws ServletException{

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		//月初と月末の日付を取得
		LocalDate start =  date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate finish = date.with(TemporalAdjusters.lastDayOfMonth());

		try{

			//DBと接続する
			con = DBUtils.getConnection();
			sql = "select sum((unit_price * sale_number)) as allsale from sales"+
					" where sale_date between ? and ?";

			ps = con.prepareStatement(sql);

			//DBから1か月分の全体売り上げ合計を取得
			ps.setObject(1,start);
			ps.setObject(2,finish);

			rs = ps.executeQuery();

			//DBの値の取り出し
			rs.next();
			long allSale = rs.getLong("allsale");

			//値をServletに送信
			return allSale;

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}
	}

	public LocalDate date (String button,String getDate) {

		try {

			LocalDate date = LocalDate.parse(getDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			//buttonの値によって処理が変わる

			if(button.equals("lastmonth")) {
				return date.minusMonths(1);

			}else if(button.equals("lastyear")) {
				return date.minusYears(1);

			}else if(button.equals("nextmonth")) {
				return date.plusMonths(1);

			}else if(button.equals("nextyear")){
				return date.plusYears(1);
			}

		}catch(Exception e) {

		}
		//どこにも当てはまらない場合は現在の日付を返す
		return LocalDate.now();
	}


}
