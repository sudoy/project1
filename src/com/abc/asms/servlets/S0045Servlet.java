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

import com.abc.asms.forms.S0045Form;
import com.abc.asms.services.MailService;
import com.abc.asms.services.S0045Service;
@WebServlet("/S0045.html")
public class S0045Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/S0045.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String mail = req.getParameter("mail");
		List<String> error = validate(mail);
		S0045Service S45S = new S0045Service();
		if(error.size()==0) {
			if(S45S.checkDB(mail)) {
				String url = req.getRequestURL().toString();
				url = url.substring(0,url.length() - 6) + "6.html?user=";
				MailService.sendMail(mail,url);
				session.setAttribute("success", "パスワード再設定メールを送信しました。");
				resp.sendRedirect("S0045.html");
				return;
			}else {
				error.add("メールアドレスを正しく入力して下さい。");
			}
		}
		S0045Form S45F = new S0045Form(mail);
		req.setAttribute("form", S45F);
		session.setAttribute("error", error);
		getServletContext().getRequestDispatcher("/WEB-INF/S0045.jsp").forward(req, resp);
	}
	/**
	 * 入力チェックメソッド
	 * @param mail 入力されたmail
	 * @return List(String)型
	 * 入力に問題があればエラーメッセージがaddされる。
	 */
	private List<String> validate(String mail) {
		List<String> error = new ArrayList<>();
		if (mail == null || mail.equals("")) {
			error.add("メールアドレスを入力して下さい。");
		} else if (100 < mail.length()) {
			error.add("メールアドレスが長すぎます。");
		} else if (!mail.matches("^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9._-]*\\.[a-zA-Z0-9._-]*$")) {
			error.add("メールアドレスを正しく入力して下さい。");
		}
		return error;
	}
}
