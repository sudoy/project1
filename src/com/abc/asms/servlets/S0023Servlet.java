package com.abc.asms.servlets;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
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
import com.abc.asms.utils.DBUtils;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet { //売上詳細編集のサーブレット

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

		//form用意
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
			form.setAccountMap(new S0023Service().getAccountMap());
			form.setCategoryMap(DBUtils.getCategoryMap(form.getCategoryId()));
		}else {
			//詳細画面から移動してきたとき…idからformを取得
			String saleId = req.getParameter("saleId");
			form = new S0023Service().findSaleDetail(saleId);
		}

		//formの中身がない場合ダッシュボードへ
		if(form == null || form.getSaleId() == null) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//formをjspに渡す
		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0023.jsp").forward(req, resp);
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

		if(isnull(form.getNote())) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//バリデーションチェック
		List<String> error = validation(form);

		if(error.isEmpty()) {
			//エラーリストがない→確認画面へ値を渡して移動
			StringBuilder input = new S0023Service().setInput(form);
			resp.sendRedirect("S0024.html?" + input);
		}else {
			//エラーリストがある→jspへform、エラーメッセージ渡してjspへ移動
			form.setAccountMap(new S0023Service().getAccountMap());
			form.setCategoryMap(DBUtils.getCategoryMap(form.getCategoryId()));
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

	//バリデーションチェック
	public List<String> validation(S0023Form form){

		List<String> error = new ArrayList<>();

		//販売日必須入力、形式
		if(form.getSaleDate() == null || form.getSaleDate().isEmpty()) {
			error.add("販売日を入力して下さい。");
		}else {
			if(form.getSaleDate().matches("^[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}$")) {
				try {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu/M/d").withResolverStyle(ResolverStyle.STRICT);
					LocalDate.parse(form.getSaleDate(),f);
				} catch (Exception e) {
					error.add("販売日を正しく入力して下さい。");
				}
			}else {
				error.add("販売日を正しく入力して下さい。");
			}
		}

		//担当必須入力、テーブル存在チェック
		if(form.getAccountId() == null || form.getAccountId().isEmpty()) {
			error.add("担当が未選択です。");
		}else {
			if(DBUtils.countAccount(form.getAccountId()) != 1) {
				error.add("アカウントテーブルに存在しません。");
			}
		}

		//カテゴリー必須入力、テーブル存在チェック
		if(form.getCategoryId() == null) {
			error.add("商品カテゴリーが未選択です。");
		}else {
			if(DBUtils.countCategory(form.getCategoryId()) != 1) {
				error.add("商品カテゴリーテーブルに存在しません。");
			}
		}

		//商品名必須入力、長さ(バイト数)
		if(form.getTradeName() == null || form.getTradeName().isEmpty()) {
			error.add("商品名を入力して下さい。");
		}else if(101 <= form.getTradeName().getBytes(Charset.forName("UTF-8")).length) {
			error.add("商品名が長すぎます。");
		}

		//単価必須入力、形式、長さ(バイト数)
		if(form.getUnitPrice() == null || form.getUnitPrice().isEmpty()) {
			error.add("単価を入力して下さい。");
		}else if(!form.getUnitPrice().matches("^[1-9][0-9]*$")) {
			error.add("単価を正しく入力して下さい。");
		}else if(10 <= form.getUnitPrice().getBytes(Charset.forName("UTF-8")).length) {
			error.add("単価が長すぎます。");
		}

		//個数必須入力、形式、長さ(バイト数)
		if(form.getSaleNumber() == null || form.getSaleNumber().isEmpty()) {
			error.add("個数を入力して下さい。");
		}else if(!form.getSaleNumber().matches("^[1-9][0-9]*$")) {
			error.add("個数を正しく入力して下さい。");
		}else if(10 <= form.getSaleNumber().getBytes(Charset.forName("UTF-8")).length) {
			error.add("個数が長すぎます。");
		}

		//備考長さ
		if(401 <= form.getNote().getBytes(Charset.forName("UTF-8")).length) {
			error.add("備考が長すぎます。");
		}

		return error;
	}

	public boolean isnull(String note) {
		//nullチェック
		if(note == null) {
			return true;
		}
		return false;
	}
}
