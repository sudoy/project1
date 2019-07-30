package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
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

	//グラフで使用
	public StringBuilder findAllSalesList(int year) throws ServletException{

		//引数の年の月ごとの売上リスト
		long[] list = new long[12];
		for(int i = 1; i <= 12; i++) {
			LocalDate monthOfYear = LocalDate.of(year, i, 1);
			list[i] = (findAllsale(monthOfYear));
		}
		return createDataOfjs(list);
	}

	//グラフで使用
	public long getMaxSale(int thisYear, int lastYear) throws ServletException {

		long max = 0;//最大値
		for(int i = 1; i <= 12; i++) {

			//今年（基準年）の月ごとの売上
			LocalDate monthOfThisYear = LocalDate.of(thisYear, i, 1);
			long thisYearSum = findAllsale(monthOfThisYear);
			//前年の月ごとの売上
			LocalDate monthOfLastYear = LocalDate.of(lastYear, i, 1);
			long lastYearSum = findAllsale(monthOfLastYear);

			//比較
			long bigger = thisYearSum <= lastYearSum ? lastYearSum : thisYearSum;
			max = bigger <= max ? max : bigger;
		}
		return (long) Math.ceil((double) max / 10000);//切り上げ
	}

	//グラフ用　まとめ
	public C0020Form getGraphData(int thisYear, C0020Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			//DB接続
			con = DBUtils.getConnection();

			//SQL作成、実行
			sql = "SELECT DATE_FORMAT(sale_date, '%Y-%m') AS sale_date, SUM(unit_price * sale_number) AS sum "
					+ "FROM sales "
					+ "WHERE sale_date BETWEEN ? AND ? "
					+ "GROUP BY DATE_FORMAT(sale_date, '%Y%m')";
			ps = con.prepareStatement(sql);
			ps.setString(1, (thisYear - 1) + "/1/1");
			ps.setString(2, thisYear + "/12/31");
			rs = ps.executeQuery();

			//月別売上リストと最大値取得
			long[] thisYearlist = new long[12];
			long[] lastYearlist = new long[12];
			long maxSale = 0;
			while(rs.next()) {
				//今年の売上
				if(rs.getString("sale_date").contains(thisYear + "-")) {
					int index = Integer.valueOf(rs.getString("sale_date").substring(rs.getString("sale_date").indexOf("-") + 1)) - 1;
					thisYearlist[index] = rs.getLong("sum");
				}
				//前年の売上
				if(rs.getString("sale_date").contains((thisYear - 1) + "-")) {
					int index = Integer.valueOf(rs.getString("sale_date").substring(rs.getString("sale_date").indexOf("-") + 1)) - 1;
					lastYearlist[index] = rs.getLong("sum");
				}
				//最大値
				maxSale = maxSale <= rs.getLong("sum") ? rs.getLong("sum") : maxSale;
			}

			//formにセット
			form.setThisYearList(createDataOfjs(thisYearlist));
			form.setLastYearList(createDataOfjs(lastYearlist));
			form.setMaxSale((long) Math.ceil((double) maxSale / 10000));

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}
		return form;
	}

	//グラフ用　文字列作成
	public StringBuilder createDataOfjs(long[] list) {

		//万で割り、小数点第二位で四捨五入して文字列に追加
		StringBuilder sb = new StringBuilder("[");
		DecimalFormat df = new DecimalFormat("#.#");
		for(long l : list) {
			sb.append(df.format((double)l / 10000) + ",");
		}
		sb.deleteCharAt(sb.length() - 1);//最後のカンマ消す
		sb.append("]");

		return sb;
	}

}
