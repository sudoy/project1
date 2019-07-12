package com.abc.asms.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		List<String> error = validate(date, accountId, tradeName, note);
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
	/**
	 * 入力チェック
	 * @param date 入力された日付
	 * @param accountId 入力されたaccountId
	 * @param tradeName 入力されたtradeName
	 * @param note 入力されたnote
	 * @return エラーメッセージ
	 */
	public List<String> validate(String[] date, String accountId, String tradeName, String note) {
		List<String> error = new ArrayList<>();
		Date date1 = null;
		Date date2 = null;
		if (date == null || date.length != 2 || accountId == null || tradeName == null || note == null) {
			error.add("バリデーションエラー");
		} else {
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				if (!date[0].matches("[0-9]{4}/([0-9]{2}|[0-9])/([0-9]{2}|[0-9])")) {
					throw new Exception();
				}
				format.setLenient(false);
				date1 = format.parse(date[0]);
			} catch (Exception e) {
				error.add("販売日（検索開始日）を正しく入力して下さい。");
			}
			try {
				if (!date[1].matches("[0-9]{4}/([0-9]{2}|[0-9])/([0-9]{2}|[0-9])")) {
					throw new Exception();
				}
				format.setLenient(false);
				date2 = format.parse(date[1]);
			} catch (Exception e) {
				error.add("販売日（検索終了日）を正しく入力して下さい。");
			}
		}
		if (error.size() == 0) {
			if (date2.before(date1)) {
				error.add("販売日（検索開始日）が販売日（検索終了日）より後の日付となっています。");
			}
		}
		return error;
	}
}
