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
import com.abc.asms.services.S0011Service;
import com.abc.asms.utils.DBUtils;

@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//session開始
		HttpSession session = req.getSession();

		//権限チェック
		AccountForm account = (AccountForm) session.getAttribute("account");
		int salesAuthority = account.getAuthority();
		if(salesAuthority != 1 && salesAuthority != 11) {

			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//S0010の入力データを取得
		String saleDate = req.getParameter("saleDate");
		String accountId = req.getParameter("accountId");
		String categoryId = req.getParameter("categoryId");
		String tradeName = req.getParameter("tradeName");
		String unitPrice = req.getParameter("unitPrice");
		String saleNumber = req.getParameter("saleNumber");
		String note = req.getParameter("note");

		//値をformにセット
		EntrySaleForm form = new EntrySaleForm(saleDate,accountId,categoryId,tradeName,unitPrice,saleNumber,note);
		List<String> error = new ArrayList<>();


		//URLなどで不正な値が入力されていないかチェック
		if(validation(form)) {
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//小計を計算するメソッドの呼び出し
		S0011Service service = new S0011Service();
		String subTotal = service.calc(unitPrice,saleNumber);
		form.setSubTotal(subTotal);


		//選択肢のリストを抽出
		List<EntrySaleForm> accountList = new ArrayList<>();
		List<EntrySaleForm> categoryList = new ArrayList<>();

		accountList = DBUtils.findAccount();
		categoryList = DBUtils.findCategory();

		//キャンセルがあったときに送るデータ
		StringBuilder cancelData = DBUtils.sendData(form);
		form.setCancelData(cancelData);

		//formをjspへ送信
		req.setAttribute("form", form);
		req.setAttribute("accountList", accountList);
		req.setAttribute("categoryList", categoryList);

		getServletContext().getRequestDispatcher("/WEB-INF/S0011.jsp").forward(req, resp);


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		//S0010で入力された値を取得

		String saleDate = req.getParameter("saleDate");
		String accountId = req.getParameter("accountId");
		String categoryId = req.getParameter("categoryId");
		String tradeName = req.getParameter("tradeName");
		String unitPrice = req.getParameter("unitPrice");
		String saleNumber = req.getParameter("saleNumber");
		String note = req.getParameter("note");

		EntrySaleForm form = new EntrySaleForm(saleDate,accountId,categoryId,tradeName,unitPrice,saleNumber,note);
		List<String> error = new ArrayList<>();


		//URLなどで不正な値が入力されていないかチェック
		if(validation(form)) {
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//insert開始
		S0011Service service = new S0011Service();
		int id = service.insert(form);

		//成功メッセージ
		session.setAttribute("success", "No" + id + "の売上を登録しました。");
		resp.sendRedirect("S0010.html");

	}

	public boolean validation(EntrySaleForm form){
		boolean b = false;

		//nullチェック、日付必須入力、
		if(form.getSaleDate() == null || form.getSaleDate().isEmpty()) {
			b = true;
		}else {
			if(form.getSaleDate().matches("^[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}$")) {
				try {
					DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu/M/d").withResolverStyle(ResolverStyle.STRICT);
					LocalDate.parse(form.getSaleDate(),f);
				} catch (Exception e) {
					e.printStackTrace();
					b = true;
				}
			}else {
				b = true;
			}
		}

		//nullチェック、担当必須入力
		if(form.getAccountId() == null || form.getAccountId().isEmpty()) {
			b = true;
		}else {

			//関連チェック
			if(DBUtils.countAccount(form.getAccountId()) != 1) {
				b = true;
			}

		}

		//nullチェック、商品カテゴリー必須入力
		if(form.getCategoryId() == null || form.getCategoryId().isEmpty()) {
			b = true;
		}else {

			//関連チェック
			if(DBUtils.countCategory(form.getCategoryId()) != 1) {
				b = true;
			}
		}

		//nullチェック、商品名必須入力、長さ(バイト数)
		if(form.getTradeName() == null || form.getTradeName().isEmpty()) {
			b = true;
		}else if(101 <= form.getTradeName().getBytes(Charset.forName("UTF-8")).length) {
			b = true;
		}

		//nullチェック、単価必須入力、形式、長さ(バイト数)
		if(form.getUnitPrice() == null || form.getUnitPrice().isEmpty()) {
			b = true;
		}else if(!form.getUnitPrice().matches("^[1-9][0-9]*$")) {
			b = true;
		}else if(10 <= form.getUnitPrice().getBytes(Charset.forName("UTF-8")).length) {
			b = true;
		}

		//nullチェック、個数必須入力、形式、長さ(バイト数)
		if(form.getSaleNumber() == null || form.getSaleNumber().isEmpty()) {
			b = true;
		}else if(!form.getSaleNumber().matches("^[1-9][0-9]*$")) {
			b = true;
		}else if(10 <= form.getSaleNumber().getBytes(Charset.forName("UTF-8")).length) {
			b = true;
		}

		//nullチェック、備考長さ
		if(form.getNote() == null || 401 <= form.getNote().getBytes(Charset.forName("UTF-8")).length) {
			b = true;
		}

		return b;

	}

}
