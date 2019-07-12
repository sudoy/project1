package com.abc.asms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.EntrySaleDataForm;
import com.abc.asms.services.S0011Service;

@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//formをsessionで取得
		HttpSession session = req.getSession();
		EntrySaleDataForm form = (EntrySaleDataForm) session.getAttribute("form");

		//小計の計算に利用
		String unitPrice = form.getUnitPrice();
		String saleNumber = form.getSaleNumber();

		//小計を計算するメソッドの呼び出し
		S0011Service service = new S0011Service();
		String subtotal = service.calc(unitPrice,saleNumber);

		//計算結果をset
		req.setAttribute("subtotal", subtotal);

		getServletContext().getRequestDispatcher("/WEB-INF/S0011.jsp").forward(req, resp);


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		int id = 0;

		EntrySaleDataForm form = (EntrySaleDataForm) session.getAttribute("form");

		//insert開始
		S0011Service service = new S0011Service();
		id = service.insert(form);

		//成功メッセージ
		session.setAttribute("success", "No" + id + "の売上を登録しました。");
		resp.sendRedirect("S0010.html");

	}


}
