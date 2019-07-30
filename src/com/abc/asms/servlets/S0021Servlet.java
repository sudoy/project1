package com.abc.asms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.SaleConditionalForm;
import com.abc.asms.services.S0021Service;

@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		S0021Service S21S = new S0021Service();

		// 検索条件のセッションが存在しているか
		if (session.getAttribute("SaleConditional") == null) {
			SaleConditionalForm scf = new SaleConditionalForm(new String[]{"",""}, "", null, "", "");
			session.setAttribute("SaleConditional", scf);
		}

		//ソートの情報を取得
		String item = (String) req.getAttribute("item");
		String sort = (String) req.getAttribute("sort");
		int page = 0;
		if(req.getParameter("page") == null) {
			page = 1;
		}else {
			page = Integer.parseInt(req.getParameter("page"));
		}

		SaleConditionalForm scf = (SaleConditionalForm) session.getAttribute("SaleConditional");
		scf.setItem(item);
		scf.setSort(sort);
		scf.setPage(page);

		req.setAttribute("list", S21S.getDB(scf));
		req.setAttribute("form", scf);
		getServletContext().getRequestDispatcher("/WEB-INF/S0021.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		S0021Service S21S = new S0021Service();

		// 検索条件のセッションが存在しているか
		if (session.getAttribute("SaleConditional") == null) {
			SaleConditionalForm scf = new SaleConditionalForm(new String[]{"",""}, "", null, "", "");
			session.setAttribute("SaleConditional", scf);
		}

        //ソートの情報を取得
		String item = (String) req.getParameter("item");
		String sort = (String) req.getParameter("sort");

		//formに代入
		SaleConditionalForm scf = (SaleConditionalForm) session.getAttribute("SaleConditional");
		scf.setItem(item);
		scf.setSort(sort);

		req.setAttribute("list", S21S.getDB(scf));
		req.setAttribute("form", scf);
		session.setAttribute("SaleConditional", scf);

		getServletContext().getRequestDispatcher("/WEB-INF/S0021.jsp").forward(req, resp);




	}
}
