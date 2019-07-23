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
import com.abc.asms.forms.EntrySaleForm;
import com.abc.asms.utils.DBUtils;

@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		//権限チェック
		AccountForm account = (AccountForm) session.getAttribute("accounts");
		int salesAuthority = account.getAuthority();
		if(salesAuthority != 1 && salesAuthority != 11) {

			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}


		//キャンセルで戻ったときの値の保持
		if(req.getParameter("cancel") != null) {
			String saleDate = req.getParameter("saleDate");
			String accountId = req.getParameter("accountId");
			String categoryId = req.getParameter("categoryId");
			String tradeName = req.getParameter("tradeName");
			String unitPrice = req.getParameter("unitPrice");
			String saleNumber = req.getParameter("saleNumber");
			String note = req.getParameter("note");

			//前回に入力された値を送信
			EntrySaleForm form = new EntrySaleForm(saleDate,accountId,categoryId,tradeName,unitPrice,saleNumber,note);
			req.setAttribute("form", form);

		//ダッシュボードから入ってきたとき
		}else {

			//登録画面に必要な値を取得
			LocalDate date = LocalDate.now();
			String saleDate = DBUtils.dateFormat(date.toString());

			//日付情報のみをformに代入して送信
			EntrySaleForm form = new EntrySaleForm();
			form.setSaleDate(saleDate);
			req.setAttribute("form", form);
		}

		//登録画面に必要な値をServiceから取得
		List<EntrySaleForm> categoryList = new ArrayList<>();
		List<EntrySaleForm> accountList = new ArrayList<>();

		categoryList = DBUtils.findCategory();
		accountList = DBUtils.findAccount();

		//jspへ送信して表示

		req.setAttribute("categoryList", categoryList);
		req.setAttribute("accountList", accountList);

		getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		//jspで入力された値を取得
		String saleDate = req.getParameter("saleDate");
		String accountId = req.getParameter("accountId");
		String categoryId = req.getParameter("categoryId");
		String tradeName = req.getParameter("tradeName");
		String unitPrice = req.getParameter("unitPrice");
		String saleNumber = req.getParameter("saleNumber");
		String note = req.getParameter("note");


		//formに代入してvalidationメソッドへ通す
		EntrySaleForm form = new EntrySaleForm(saleDate,accountId,categoryId,tradeName,unitPrice,saleNumber,note);
		List<String>  error = new ArrayList<>();


		if(isnull(note)) {
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
		}

		//validationチェック
		error = validation(form);

		//エラーメッセージがなければページ遷移
		if(error.isEmpty()) {

			//URLと一緒jにformをリダイレクト
			StringBuilder sendData = new StringBuilder();
			sendData = DBUtils.sendData(form);

			resp.sendRedirect("S0011.html?" + sendData);

		//エラーがあれば同じページへ
		}else{

			//選択肢のリストと入力した値を再送信
			List<EntrySaleForm> categoryList = new ArrayList<>();
			List<EntrySaleForm> accountList = new ArrayList<>();

			categoryList = DBUtils.findCategory();
			accountList = DBUtils.findAccount();

			session.setAttribute("error", error);
			req.setAttribute("form", form);
			req.setAttribute("categoryList", categoryList);
			req.setAttribute("accountList", accountList);

			getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);

		}

	}

	public List<String> validation(EntrySaleForm form){
		List<String> error = new ArrayList<>();


		//nullチェック、日付必須入力、
		if(form.getSaleDate() == null || form.getSaleDate().isEmpty()) {
			error.add("販売日を入力して下さい。");
		}else {
			if(form.getSaleDate().matches("^[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}$")) {
				try {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu/M/d").withResolverStyle(ResolverStyle.STRICT);
					LocalDate.parse(form.getSaleDate(),f);
				} catch (Exception e) {
					e.printStackTrace();
					error.add("販売日を正しく入力して下さい。");
				}
			}else {
				error.add("販売日を正しく入力して下さい。");
			}
		}


		//nullチェック、担当必須入力
		if(form.getAccountId() == null || form.getAccountId().isEmpty()) {
			error.add("担当が未選択です。");
		}else {

			//関連チェック
			if(DBUtils.countAccount(form.getAccountId()) != 1) {
				error.add("アカウントテーブルに存在しません。");
			}

		}

		//nullチェック、商品カテゴリー必須入力
		if(form.getCategoryId() == null || form.getCategoryId().isEmpty()) {
			error.add("商品カテゴリーが未選択です。");
		}else {

			//関連チェック
			if(DBUtils.countCategory(form.getCategoryId()) != 1) {
				error.add("商品カテゴリーテーブルに存在しません。");
			}
		}

		//nullチェック、商品名必須入力、長さ(バイト数)
		if(form.getTradeName() == null || form.getTradeName().isEmpty()) {
			error.add("商品名を入力して下さい。");
		}else if(101 <= form.getTradeName().getBytes(Charset.forName("UTF-8")).length) {
			error.add("商品名が長すぎます。");
		}

		//nullチェック、単価必須入力、形式、長さ(バイト数)
		if(form.getUnitPrice() == null || form.getUnitPrice().isEmpty()) {
			error.add("単価を入力して下さい。");
		}else if(!form.getUnitPrice().matches("^[1-9][0-9]*$")) {
			error.add("単価を正しく入力して下さい。");
		}else if(10 <= form.getUnitPrice().getBytes(Charset.forName("UTF-8")).length) {
			error.add("単価が長すぎます。");
		}

		//nullチェック、個数必須入力、形式、長さ(バイト数)
		if(form.getSaleNumber() == null || form.getSaleNumber().isEmpty()) {
			error.add("個数を入力して下さい。");
		}else if(!form.getSaleNumber().matches("^[1-9][0-9]*$")) {
			error.add("個数を正しく入力して下さい。");
		}else if(10 <= form.getSaleNumber().getBytes(Charset.forName("UTF-8")).length) {
			error.add("個数が長すぎます。");
		}



		//nullチェック済み、備考長さ
		if(401 <= form.getNote().getBytes(Charset.forName("UTF-8")).length) {
			error.add("備考が長すぎます。");
		}

		return error;

	}

	public boolean isnull(String note) {
		//nullチェック
		//エラーがある場合はダッシュボード
		if(note == null) {
			return true;
		}

		return false;
	}

}
