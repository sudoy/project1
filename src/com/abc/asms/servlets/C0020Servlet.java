package com.abc.asms.servlets;

import java.io.IOException;
import java.time.LocalDate;
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

		//今日と先月の同じ日を取得
		LocalDate date = LocalDate.now();
		LocalDate beforedate = date.minusMonths(1);


		//jspへ送る変数とServiceを用意
		List<C0020Form> findList = new ArrayList<>();
		C0020Service service = new C0020Service();


		//今月と先月(数字)
		int monthval = date.getMonthValue();
		int lastmonthval = beforedate.getMonthValue();


		//今月と先月の全体売り上げ
		double salemonth = service.findAllsale(date);
		double salelastmonth = service.findAllsale(beforedate);

		//今月と先月の売り上げ比率
		double percent = salemonth / salelastmonth;

		//個人の売り上げ合計
		int total = service.findSale(accountId, date);


		//個人の売上リスト
		findList = service.find(accountId, date);


		req.setAttribute("lastmonthval", lastmonthval);
		req.setAttribute("monthval", monthval);
		req.setAttribute("account", account);
		req.setAttribute("percent", percent);
		req.setAttribute("salemonth", salemonth);
		req.setAttribute("salelastmonth", salelastmonth);
		req.setAttribute("findList", findList);
		req.setAttribute("total", total);


		getServletContext().getRequestDispatcher("/WEB-INF/C0020.jsp").forward(req, resp);

		session.setAttribute("success", null);
		session.setAttribute("error", null);
	}


}
