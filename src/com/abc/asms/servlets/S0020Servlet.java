package com.abc.asms.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.SaleConditionalForm;
import com.abc.asms.services.S0020Service;

@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		S0020Service S20S = new S0020Service();

		// 現在の日時の取得と設定
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'/'M'/'d");
		req.setAttribute("saleDate1", sdf.format(date));
		req.setAttribute("saleDate2", sdf.format(date));

		// 担当の取得と設定
		req.setAttribute("accountMap", S20S.getMap("account_id", "name", "accounts"));

		// カテゴリの取得と設定
		req.setAttribute("categoryMap", S20S.getMap("category_id", "category_name", "categories"));
		getServletContext().getRequestDispatcher("/WEB-INF/S0020.jsp").forward(req, resp);

		HttpSession session = req.getSession();
		session.setAttribute("error", null);
		session.setAttribute("success", null);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String date1 = req.getParameter("saleDate1");
		String date2 = req.getParameter("saleDate2");
		String[] date = { date1, date2 };
		String accountId = req.getParameter("accountId");
		String categoryId[] = req.getParameterValues("categoryId");
		String tradeName = req.getParameter("tradeName");
		String note = req.getParameter("note");
		S0020Service S20S = new S0020Service();

		// 入力チェック
		List<String> error = S20S.validate(date, accountId, tradeName, note);
		if (error.size() == 0) {

			// エラーがない時
			SaleConditionalForm scf = new SaleConditionalForm(date, accountId, categoryId, tradeName, note);
			int length = S20S.checkListSize(scf);
			if (length != 0) {

				// 該当データが存在しているとき
				session.setAttribute("SaleConditional", scf);
				resp.sendRedirect("S0021.html"); // 検索一覧へ
				return;
			} else {

				// 該当データが存在していないとき
				error.add("検索結果はありません。");
			}
		}

		//エラー時処理 入力を返す
		req.setAttribute("saleDate1", date1);
		req.setAttribute("saleDate2", date2);
		req.setAttribute("accountId", accountId);
		req.setAttribute("categoryId", categoryId);
		req.setAttribute("tradeName", tradeName);
		req.setAttribute("note", note);
		req.setAttribute("accountMap", S20S.getMap("account_id", "name", "accounts"));
		req.setAttribute("categoryMap", S20S.getMap("category_id", "category_name", "categories"));
		session.setAttribute("error", error);

		getServletContext().getRequestDispatcher("/WEB-INF/S0020.jsp").forward(req, resp);
		session.setAttribute("error", null);
	}
}
