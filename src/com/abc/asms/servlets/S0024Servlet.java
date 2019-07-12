package com.abc.asms.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.AccountForm;
import com.abc.asms.forms.S0023Form;
import com.abc.asms.forms.S0024Form;
import com.abc.asms.services.S0023Service;
import com.abc.asms.services.S0024Service;

@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//権限チェック 売上登録権限がなかったらダッシュボードへ
		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("account");
		int salesAuthority = account.getAuthority();
		if(salesAuthority != 1 && salesAuthority != 11) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//S0023から値を取得
		S0024Form form = new S0024Form();
		form.setSaleId(req.getParameter("saleId"));
		form.setSaleDate(req.getParameter("saleDate"));
		form.setAccountId(req.getParameter("accountId"));
		form.setName(new S0024Service().getName(form.getAccountId()));
		form.setCategoryId(req.getParameter("categoryId"));
		form.setCategoryName(new S0024Service().getCategoryName(form.getCategoryId()));
		if(req.getParameter("tradeName") != null) {
			form.setTradeName(URLDecoder.decode(req.getParameter("tradeName"), "UTF-8"));
		}else {
			form.setTradeName(req.getParameter("tradeName"));
		}
		form.setUnitPrice(req.getParameter("unitPrice"));
		form.setSaleNumber(req.getParameter("saleNumber"));
		if(req.getParameter("note") != null) {
			form.setNote(URLDecoder.decode(req.getParameter("note"), "UTF-8"));
		}else {
			form.setNote(req.getParameter("note"));
		}
		form.setInput();
		form.setSubtotal();
		S0023Service s = new S0023Service();
		form.setCategoryMap(s.getCategoryMap(form.getCategoryId()));

		//JSPへ
		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0024.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//権限チェック 売上登録権限がなかったらダッシュボードへ
		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("account");
		int salesAuthority = account.getAuthority();
		if(salesAuthority != 1 && salesAuthority != 11) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//入力情報取得
		S0024Form form = new S0024Form();
		form.setSaleId(req.getParameter("saleId"));
		form.setSaleDate(req.getParameter("saleDate"));
		form.setAccountId(req.getParameter("accountId"));
		form.setCategoryId(req.getParameter("categoryId"));
		form.setTradeName(req.getParameter("tradeName"));
		form.setUnitPrice(req.getParameter("unitPrice"));
		form.setSaleNumber(req.getParameter("saleNumber"));
		form.setNote(req.getParameter("note"));

		//最終確認
		S0023Form form23 = new S0023Form(form.getSaleId(), form.getSaleDate(), form.getAccountId(),
		form.getCategoryId(), form.getTradeName(), form.getUnitPrice(), form.getSaleNumber(), form.getNote());
		List<String> error = new S0023Service().validation(form23);

		if(error.isEmpty()) {
			//更新
			new S0024Service().update(form);
			//成功メッセージ
			List<String> success = new ArrayList<>();
			success.add("No"+ form.getSaleId() +"の売上を更新しました。");
			session.setAttribute("success", success);
		}else {
			//エラーメッセージ
			error.add("No"+ form.getSaleId() +"の売上の更新ができませんでした。");
			session.setAttribute("error", error);
		}

		//検索結果一覧へ戻る
		resp.sendRedirect("S0021.html");
	}
}
