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
import com.abc.asms.forms.S0025Form;
import com.abc.asms.services.S0025Service;

@WebServlet("/S0025.html")
public class S0025Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//権限チェック 売上登録権限がなかったらダッシュボードへ
		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("accounts");
		int salesAuthority = account.getAuthority();
		if(salesAuthority != 1 && salesAuthority != 11) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//id取得
		String saleId = req.getParameter("saleId");

		//form作成
		S0025Form form = new S0025Service().findSaleDetail(saleId);

		//formの中身がない場合ダッシュボードへ
		if(form == null || form.getSaleId() == null) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//jspへ渡す
		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0025.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//権限チェック 売上登録権限がなかったらダッシュボードへ
		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("accounts");
		int salesAuthority = account.getAuthority();
		if(salesAuthority != 1 && salesAuthority != 11) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//id取得
		String saleId = req.getParameter("saleId");
		int version = numCheck(req.getParameter("version"));

		//削除処理
		int cnt = new S0025Service().delete(saleId,version);

		//成功メッセージ
		if(cnt == 1) {
			new S0025Service().updateHistory(account.getAccountId(), saleId);
			List<String> success = new ArrayList<>();
			success.add("No"+ saleId +"の売上を削除しました。");
			session.setAttribute("success", success);
		}else {
			List<String> error = new ArrayList<>();
			error.add("No"+ saleId +"の削除に失敗しました。");
			session.setAttribute("error", error);
			resp.sendRedirect("S0022.html?saleId=" + saleId);
			return;
		}

		//売上検索結果一覧へ
		resp.sendRedirect("S0021.html");
	}

	private int numCheck(String num) {
		if(num.matches("^[1-9][0-9]*$")) {
			return Integer.valueOf(num);
		}
		return 0;
	}
}
