package com.abc.asms.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.services.S0020Service;

@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	S0020Service S20S = new S0020Service();
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy'/'M'/'d");
	req.setAttribute("saleDate1",sdf.format(date));
	req.setAttribute("saleDate2",sdf.format(date));
	req.setAttribute("accountMap", S20S.getMap("account_id","name","accounts"));
	req.setAttribute("categoryMap", S20S.getMap("category_id","category_name","categories"));
	getServletContext().getRequestDispatcher("/WEB-INF/S0020.jsp").forward(req, resp);
	HttpSession session =req.getSession();
	session.setAttribute("error", "");
	}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String date1 = req.getParameter("saleDate1");
		req.setAttribute("saleDate1",date1);
		String date2 = req.getParameter("saleDate2");
		req.setAttribute("saleDate2",date2);
		String[] date = {date1,date2};
		String accountId = req.getParameter("accountId");
		req.setAttribute("accountId",accountId);
		String categoryId[] = req.getParameterValues("categoryId");
		req.setAttribute("categoryId",categoryId);
		String tradeName = req.getParameter("tradeName");
		req.setAttribute("tradeName",tradeName);
		String note = req.getParameter("note");
		req.setAttribute("note",note);
		S0020Service S20S = new S0020Service();
		req.setAttribute("accountMap", S20S.getMap("account_id","name","accounts"));
		req.setAttribute("categoryMap", S20S.getMap("category_id","category_name","categories"));
		// 入力チェック
		List<String> error = validate(date);
		if(0<error.size()) {
			session.setAttribute("error",error);
			getServletContext().getRequestDispatcher("/WEB-INF/S0020.jsp").forward(req, resp);
			session.setAttribute("error", "");
			return;
		}

		int length = S20S.checkListSize(date, accountId, categoryId, tradeName, note);



		// lengthが0なら該当データ無し→エラー
		if(length==0) {
			error.add("検索結果はありません。");
			session.setAttribute("error",error);
			getServletContext().getRequestDispatcher("/WEB-INF/S0020.jsp").forward(req, resp);
			session.setAttribute("error", "");
			return;
		}

		resp.sendRedirect("S0021.html");
	}

	private List<String> validate(String[] date) {
		List<String> error = new ArrayList<>();
		Date date1 = null ;
		Date date2 = null;
		if(date==null||date.length!=2) {
			error.add("販売日の入力が間違っています");
		}else {
			DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
			try {
				if(!date[0].matches("[0-9]{4}/([0-9]{2}|[0-9])/([0-9]{2}|[0-9])")) {
					throw new Exception();
				}
				format.setLenient(false);
				date1 = format.parse(date[0]);
				} catch (Exception e) {
					error.add("販売日（検索開始日）を正しく入力して下さい。");
			}
			try {
				if(!date[1].matches("[0-9]{4}/([0-9]{2}|[0-9])/([0-9]{2}|[0-9])")) {
					throw new Exception();
					}
				format.setLenient(false);
	            date2 = format.parse(date[1]);
				} catch (Exception e) {
					error.add("販売日（検索終了日）を正しく入力して下さい。");
			}
		}
		if(error.size()==0) {
			if(date2.before(date1)) {
				error.add("販売日（検索開始日）が販売日（検索終了日）より後の日付となっています。");
			}
		}
		return error;
	}
}
