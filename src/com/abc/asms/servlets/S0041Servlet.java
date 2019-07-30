package com.abc.asms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.AccountConditionalForm;
import com.abc.asms.services.S0041Service;

@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		S0041Service S41S = new S0041Service();
		HttpSession session = req.getSession();

		// 検索条件のセッションが存在しているか
		if (session.getAttribute("AccountConditional") == null) {
			AccountConditionalForm acf = new AccountConditionalForm("", "", "", "");
			session.setAttribute("AccountConditional", acf);
		}

		AccountConditionalForm acf = (AccountConditionalForm) session.getAttribute("AccountConditional");
		req.setAttribute("list", S41S.getDB(acf));
		getServletContext().getRequestDispatcher("/WEB-INF/S0041.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		S0041Service S41S = new S0041Service();
		HttpSession session = req.getSession();

		// 検索条件のセッションが存在しているか
		if (session.getAttribute("AccountConditional") == null) {
			resp.sendRedirect("S0041.html");
			return;
		}

		AccountConditionalForm acf = (AccountConditionalForm) session.getAttribute("AccountConditional");
		// 文字コード設定
		resp.setContentType("text/html; charset=SJIS");
		// ファイル名設定（ファイル名を設定しないと、htmlとして画面に表示されてしまいます
		resp.setHeader("Content-Disposition", "attachment; filename=\"dynamic.csv\"");
		// レスポンスにCSV出力
		PrintWriter w = resp.getWriter();
		w.print(S41S.getCSV(acf));
		w.flush();
	}
}
