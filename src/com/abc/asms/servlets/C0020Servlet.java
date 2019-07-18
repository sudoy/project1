package com.abc.asms.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.AccountForm;
import com.abc.asms.forms.C0020Form;
import com.abc.asms.services.C0020Service;


@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインした情報からidを取得
		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("account");
		int accountId = account.getAccountId();

		//変数とServiceを用意
		List<C0020Form> findList = new ArrayList<>();
		C0020Service service = new C0020Service();
		LocalDate date;
		LocalDate beforedate;


		//ダッシュボードのボタンを押したとき
		if(req.getParameter("button") != null) {

			String button = req.getParameter("button");
			String getdate = req.getParameter("date");

			//不正な日付がないかチェック
			if(checkdate(getdate)) {
				resp.sendRedirect("C0020.html");
				return;
			}

			date = service.date(button, getdate);
			beforedate = date.minusMonths(1);

		//ログインしてきたとき(1回目)
		}else{
			date = LocalDate.now();
			beforedate = date.minusMonths(1);
		}


		//今月と先月の全体売り上げ
		double salemonth = service.findAllsale(date);
		double salelastmonth = service.findAllsale(beforedate);

		//今月と先月の売り上げ比率
		double percent = salemonth / salelastmonth;

		//個人の売上リスト
		findList = service.find(accountId, date);

		//個人の売り上げ合計
		int total = service.findSale(accountId, date);

		//formへ代入
		C0020Form form = new C0020Form(date,beforedate,salemonth,salelastmonth,percent,total);

		//formをjspへ送信
		req.setAttribute("form", form);
		req.setAttribute("account", account);
		req.setAttribute("findList", findList);


		getServletContext().getRequestDispatcher("/WEB-INF/C0020.jsp").forward(req, resp);

	}


	//バリデーションチェック
	public boolean checkdate(String date) {
		//URLでデータを送信する際に不正な日付がないかチェック
		boolean b = false;
		try {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT);
			LocalDate.parse(date,f);
		} catch (Exception e) {
			b = true;
		}

		return b;
	}


}
