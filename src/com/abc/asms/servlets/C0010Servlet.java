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
import com.abc.asms.forms.C0010Form;
import com.abc.asms.services.C0010Service;

@WebServlet("/C0010.html")
public class C0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/C0010.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		// 入力チェック
		List<String> error = validate(mail, password);
		if (error.size() == 0) {
			C0010Service c10s = new C0010Service();
			AccountForm account = c10s.checkDB(mail, password);
			// 名前がnullではない→一致している
			if (!(account.getName()==null)) {
				// sessionにAccountFormを代入後ダッシュボードにリダイレクト
				session.setAttribute("account", account);
				resp.sendRedirect("C0020.html");
				return;
			}
			// 名前がnullなら該当データ無し→エラー
			error.add("メールアドレス、パスワードを正しく入力して下さい。");
		}
		C0010Form C10F = new C0010Form(mail, password);
		req.setAttribute("form", C10F);
		session.setAttribute("error", error);
		getServletContext().getRequestDispatcher("/WEB-INF/C0010.jsp").forward(req, resp);
	}

	/**
	 * 入力チェックメソッド
	 * @param mail 入力されたmail
	 * @param password 入力されたpassword
	 * @return List(String)型
	 * 入力に問題があればエラーメッセージがaddされる。
	 */
	private List<String> validate(String mail, String password) {
		List<String> error = new ArrayList<>();
		if (mail == null || mail.equals("")) {
			error.add("メールアドレスを入力して下さい。");
		} else if (100 < mail.length()) {
			error.add("メールアドレスが長すぎます。");
		} else if (!mail.matches("^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9._-]*\\.[a-zA-Z0-9._-]*$")) {
			error.add("メールアドレスを正しく入力して下さい。");
		}
		if (password == null || password.equals("")) {
			error.add("パスワードが未入力です。");
		} else if (30 < password.length()) {
			error.add("パスワードが長すぎます。");
		}
		return error;
	}
}
