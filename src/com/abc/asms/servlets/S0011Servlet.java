package com.abc.asms.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.AccountForm;
import com.abc.asms.forms.EntrySaleDataForm;
import com.abc.asms.services.S0011Service;

@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//session開始
		HttpSession session = req.getSession();

		//権限チェック
		AccountForm account = (AccountForm) session.getAttribute("account");
		int salesAuthority = account.getAuthority();
		if(salesAuthority != 1 && salesAuthority != 11) {

			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//S0010の入力データを取得
		EntrySaleDataForm form = (EntrySaleDataForm) session.getAttribute("form");

		//小計の計算に利用
		String unitPrice = form.getUnitPrice();
		String saleNumber = form.getSaleNumber();

		//小計を計算するメソッドの呼び出し
		S0011Service service = new S0011Service();
		String subtotal = service.calc(unitPrice,saleNumber);
		form.setSubtotal(subtotal);

		//計算結果をset
		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0011.jsp").forward(req, resp);


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		//S0010で入力された値を取得
		EntrySaleDataForm form = (EntrySaleDataForm) session.getAttribute("form");

		//insert開始
		S0011Service service = new S0011Service();
		int id = service.insert(form);

		//成功メッセージ
		session.setAttribute("success", "No" + id + "の売上を登録しました。");
		resp.sendRedirect("S0010.html");

	}


}
