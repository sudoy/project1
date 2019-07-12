package com.abc.asms.servlets;

import java.io.IOException;
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
import com.abc.asms.forms.EntrySaleDataForm;
import com.abc.asms.services.S0010Service;
import com.abc.asms.utils.DBUtils;

@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

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


		//登録画面に必要な値を取得
		LocalDate date = LocalDate.now();
		String saleDate = DBUtils.dateFormat(date.toString());


		//登録画面に必要な値をServiceから取得
		List<EntrySaleDataForm> categoryList = new ArrayList<>();
		List<EntrySaleDataForm> accountList = new ArrayList<>();
		S0010Service service = new S0010Service();

		categoryList = service.findCategory();
		accountList = service.findAccount();

		//jspへ送信して表示
		req.setAttribute("saleDate", saleDate);
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("accountList", accountList);

		getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		//jspで入力された値を取得
		String saleDate = (String) req.getParameter("saleDate");
		String accountId = (String) req.getParameter("accountId");
		String categoryId = (String) req.getParameter("categoryId");
		String tradeName = (String) req.getParameter("tradeName");
		String unitPrice = (String) req.getParameter("unitPrice");
		String saleNumber = (String) req.getParameter("saleNumber");
		String note = (String) req.getParameter("note");

		//formに代入してvalidationメソッドへ通す
		EntrySaleDataForm form = new EntrySaleDataForm(saleDate,accountId,categoryId,tradeName,unitPrice,saleNumber,note);
		S0010Service service = new S0010Service();
		List<String>  error = new ArrayList<>();

		//validationチェック
		error = validation(form);

		//関連チェック
		error.add(service.accountCheck(form));
		error.add(service.categoryCheck(form));

		//エラーメッセージがなければページ遷移
		if(error.isEmpty()) {

			session.setAttribute("form", form);
			resp.sendRedirect("S0011.html");

		//エラーがあれば同じページへ
		}else{

			req.setAttribute("form", form);
			req.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);

		}

	}

	public List<String> validation(EntrySaleDataForm form){

		List<String> error = new ArrayList<>();

		try {

			if(form.getSaleDate().isEmpty()) {
				error.add("販売日を入力して下さい。");
			}else {
				if(form.getSaleDate().matches("^[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}$")) {
					try {
						DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu/M/d").withResolverStyle(ResolverStyle.STRICT);
						LocalDate.parse(form.getSaleDate(),f);
					} catch (Exception e) {
						e.printStackTrace();
						error.add("入力した日付が不正です。");
					}
				}else {
					error.add("販売日を正しく入力して下さい。");
				}
			}

			if(form.getAccountId() == null) {
				error.add("担当が未選択です。");
			}

			if(form.getCategoryId() == null) {
				error.add("商品カテゴリーが未選択です。");
			}

			//商品名必須入力、長さ(バイト数)
			if(form.getTradeName().isEmpty()) {
				error.add("商品名を入力して下さい。");
			}else if(101 <= form.getTradeName().getBytes("UTF-8").length) {
				error.add("商品名が長すぎます。");
			}

			//単価必須入力、形式、長さ(バイト数)
			if(form.getUnitPrice().isEmpty()) {
				error.add("単価を入力して下さい。");
			}else if(!form.getUnitPrice().matches("^[1-9][0-9]*$")) {
				error.add("単価を正しく入力して下さい。");
			}else if(10 <= form.getUnitPrice().getBytes("UTF-8").length) {
				error.add("単価が長すぎます。");
			}

			//個数必須入力、形式、長さ(バイト数)
			if(form.getSaleNumber().isEmpty()) {
				error.add("個数を入力して下さい。");
			}else if(!form.getSaleNumber().matches("^[1-9][0-9]*$")) {
				error.add("個数を正しく入力して下さい。");
			}else if(10 <= form.getSaleNumber().getBytes("UTF-8").length) {
				error.add("個数が長すぎます。");
			}

			//備考長さ
			if(401 <= form.getNote().getBytes("UTF-8").length) {
				error.add("備考が長すぎます。");
			}
		}catch(Exception e) {
			e.printStackTrace();
			error.add("バリデーションエラー");
		}

		return error;


	}

}
