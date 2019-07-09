package com.abc.asms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.asms.forms.S0022Form;
import com.abc.asms.services.S0022Service;

@WebServlet("/S0022.html") //売上詳細表示のサーブレット
public class S0022Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//ログインチェック エラーで遷移

		//権限チェック setAする



		//id取得(idが空だったらエラーで戻る?）
		String saleId = req.getParameter("saleId");

		//idからformを取得
		S0022Form form = new S0022Service().findSaleDetail(saleId);

		//formをjspに渡す
		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0022.jsp").forward(req, resp);
	}
}
