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

import com.abc.asms.forms.AccountConditionalForm;
import com.abc.asms.services.S0040Service;

@WebServlet("/S0040.html")
public class S0040Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/S0040.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String name = (String) req.getParameter("name");
		String mail = (String) req.getParameter("mail");
		String salesAuthority = (String) req.getParameter("salesAuthority");
		String accountAuthority = (String) req.getParameter("accountAuthority");
		List<String> error = validate(name, mail);
		// 入力チェック
		if (error.size() == 0) {
			// エラーなし
			S0040Service S40S = new S0040Service();
			String authority = S40S.setAuthority(salesAuthority, accountAuthority);
			AccountConditionalForm acf = new AccountConditionalForm(name, mail, authority);
			// データ一致件数チェック
			if (0 < S40S.checkListSize(acf)) {
				// データ一致あり
				session.setAttribute("AccountConditional", acf);
				resp.sendRedirect("S0041.html");
				return;
			}
			error.add("検索結果はありません。");
		}
		req.setAttribute("name", name);
		req.setAttribute("mail", mail);
		req.setAttribute("salesAuthority", salesAuthority);
		req.setAttribute("accountAuthority", accountAuthority);
		session.setAttribute("error", error);
		getServletContext().getRequestDispatcher("/WEB-INF/S0040.jsp").forward(req, resp);
		session.setAttribute("error", null);
	}

	/**
	 * 入力チェックメソッド
	 * @param mail 入力されたmail
	 * @param password 入力されたpassword
	 * @return List(String)型
	 * 入力に問題があればエラーメッセージがaddされる。
	 */
	private List<String> validate(String name, String mail) {
		List<String> error = new ArrayList<>();
		if (name != null && !name.equals("")) {
			if (20 < name.length()) {
				error.add("氏名の指定が長すぎます。");
			}
		}
		if (mail != null && !mail.equals("")) {
			if (100 < mail.length()) {
				error.add("メールアドレスの指定が長すぎます。");
			} else if (!mail.matches("^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9._-]*\\.[a-zA-Z0-9._-]*$")) {
				error.add("エラーメッセージ：メールアドレスの形式が誤っています。");
			}
		}
		return error;
	}
}
