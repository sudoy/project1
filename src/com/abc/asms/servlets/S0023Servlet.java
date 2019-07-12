package com.abc.asms.servlets;

import java.io.IOException;
import java.net.URLEncoder;
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
import com.abc.asms.services.S0023Service;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet { //売上詳細編集のサーブレット

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

		S0023Form form = new S0023Form();
		if(req.getParameter("cancel") != null) {
			//確認画面からキャンセルで戻ってきたとき
			form.setSaleId(req.getParameter("saleId"));
			form.setSaleDate(req.getParameter("saleDate"));
			form.setAccountId(req.getParameter("accountId"));
			form.setCategoryId(req.getParameter("categoryId"));
			form.setTradeName(req.getParameter("tradeName"));
			form.setUnitPrice(req.getParameter("unitPrice"));
			form.setSaleNumber(req.getParameter("saleNumber"));
			form.setNote(req.getParameter("note"));
			S0023Service s = new S0023Service();
			form.setAccountMap(s.getAccountMap());
			form.setCategoryMap(s.getCategoryMap(form.getCategoryId()));
		}else {
			//詳細画面から移動してきたとき
			//id取得
			String saleId = req.getParameter("saleId");
			//idからformを取得	…該当データと、アカウント名(id)、カテゴリ名(id)のリスト
			form = new S0023Service().findSaleDetail(saleId);
		}

		//formをjspに渡す
		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0023.jsp").forward(req, resp);
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

		//入力した値を取得、form用意
		S0023Form form = new S0023Form();
		form.setSaleId(req.getParameter("saleId"));
		form.setSaleDate(req.getParameter("saleDate"));
		form.setAccountId(req.getParameter("accountId"));
		form.setCategoryId(req.getParameter("categoryId"));
		form.setTradeName(req.getParameter("tradeName"));
		form.setUnitPrice(replaceC(req.getParameter("unitPrice")));
		form.setSaleNumber(replaceC(req.getParameter("saleNumber")));
		form.setNote(req.getParameter("note"));
		if(form.getNote() == null) { form.setNote(""); }

		//バリデーションチェック
		List<String> error = new S0023Service().validation(form);


		if(error.isEmpty()) {
			//エラーリストがない→確認画面へ値を渡して移動
			String input = "saleId=" + form.getSaleId()
			+ "&saleDate=" + form.getSaleDate()
			+ "&accountId=" + form.getAccountId()
			+ "&categoryId=" + form.getCategoryId()
			+ "&tradeName=" + URLEncoder.encode(form.getTradeName(), "UTF-8")
			+ "&unitPrice=" + form.getUnitPrice()
			+ "&saleNumber=" + form.getSaleNumber()
			+ "&note=" + URLEncoder.encode(form.getNote(), "UTF-8");
			resp.sendRedirect("S0024.html?" + input);
		}else {
			//エラーリストがある→jspへformを渡して再表示、エラーメッセージ渡して表示、jspへ移動
			S0023Service s = new S0023Service();
			form.setAccountMap(s.getAccountMap());
			form.setCategoryMap(s.getCategoryMap(form.getCategoryId()));
			req.setAttribute("form", form);
			req.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/WEB-INF/S0023.jsp").forward(req, resp);
		}

	}

	public String replaceC(String num) {

		if(num != null && num.matches("^[0-9]+(,[0-9]+)*$")) {
			return num.replace(",", "");
		}
		return num;
	}
}
