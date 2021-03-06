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
import com.abc.asms.forms.S0024Form;
import com.abc.asms.services.S0024Service;
import com.abc.asms.utils.DBUtils;

@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {

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

		//S0023から値を取得
		S0024Form form = new S0024Form();
		form.setSaleId(req.getParameter("saleId"));
		form.setSaleDate(req.getParameter("saleDate"));
		form.setAccountId(req.getParameter("accountId"));
		form.setName(new S0024Service().getName(form.getAccountId()));
		form.setCategoryId(req.getParameter("categoryId"));
		form.setCategoryName(new S0024Service().getCategoryName(form.getCategoryId()));
		form.setTradeName(req.getParameter("tradeName"));
		form.setUnitPrice(req.getParameter("unitPrice"));
		form.setSaleNumber(req.getParameter("saleNumber"));
		form.setNote(req.getParameter("note"));
		form.setVersion(numCheck(req.getParameter("version")));
		form.setInput(new S0024Service().setInput(form));


		//formの中身がない場合ダッシュボードへ
		if(form == null || form.getSaleId() == null) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		List<String> error = validation(form);
		if(!error.isEmpty()) {
			error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		// 税率の取得
		String rate = DBUtils.getRate(form.getSaleDate(), form.getCategoryId());
		form.setRate(rate);

		//JSPへ
		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0024.jsp").forward(req, resp);
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
		form.setVersion(numCheck(req.getParameter("version")));


		if(isnull(form.getNote())) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//最終確認
		List<String> error = validation(form);

		if(error.isEmpty()) {
			//更新
			int cnt = new S0024Service().update(form);
			if(cnt == 1) {
				new S0024Service().insertHistory(form,account.getAccountId());
				//成功メッセージ
				List<String> success = new ArrayList<>();
				success.add("No"+ form.getSaleId() +"の売上を更新しました。");
				session.setAttribute("success", success);
			}else {
				error.add("No"+ form.getSaleId() +"の更新に失敗しました。");
				session.setAttribute("error", error);
				resp.sendRedirect("S0022.html?saleId=" + form.getSaleId());
				return;
			}
		}else {
			error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//検索結果一覧へ戻る
		resp.sendRedirect("S0021.html");
	}

	private List<String> validation(S0024Form form){

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
		if(form.getAccountId() == null) {
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

	private boolean isnull(String note) {
		//nullチェック
		if(note == null) {
			return true;
		}
		return false;
	}

	private int numCheck(String num) {
		if(num.matches("^[1-9][0-9]*$")) {
			return Integer.valueOf(num);
		}
		return 0;
	}

}
