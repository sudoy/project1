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
import com.abc.asms.forms.S0022Form;
import com.abc.asms.services.S0022Service;

@WebServlet("/S0022.html") //売上詳細表示のサーブレット
public class S0022Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//権限チェック 売上登録権限があればボタン表示
		String authorityCheck = "";
		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("account");
		int salesAuthority = account.getAuthority();
		if(salesAuthority == 1 || salesAuthority == 11) {
			authorityCheck = "ok";
		}

		//id取得
		String saleId = req.getParameter("saleId");

		//idからformを取得
		S0022Form form = new S0022Service().findSaleDetail(saleId);

		//formの中身がない場合ダッシュボードへ
		if(form == null) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		form.setSalesAuthority(authorityCheck);

		//formをjspに渡す
		req.setAttribute("form", form);

		//結合試験で利用、sessionを表示
//		Enumeration e = session.getAttributeNames();
//		while(e.hasMoreElements()) {
//			String key = (String)e.nextElement();
//			System.out.println( key + "：" + session.getAttribute(key) + "<br>");
//		}


		getServletContext().getRequestDispatcher("/WEB-INF/S0022.jsp").forward(req, resp);
	}
}
