package com.abc.asms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインしたメールアドレスをsessionで取得
		HttpSession session = req.getSession();

		//日付情報を取得
		LocalDate date = LocalDate.now();
		LocalDate lastmonth = date.minusMonths(1);

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try{

			//DBと接続する
			con = com.abc.asms.utils.DBUtils.getConnection();
			sql = "SELECT id,name,detail,priority,timelimit FROM todo ORDER BY id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();



			//DBの値の取り出し
			while(rs.next()){
				String id = rs.getString("id");


				//DBの値をセットする

			}

			//値をServletに送信


		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				com.abc.asms.utils.DBUtils.close(con, ps, rs);
			}catch (Exception e){}

		}


		getServletContext().getRequestDispatcher("/WEB-INF/C0020.jsp").forward(req, resp);
	}


}
