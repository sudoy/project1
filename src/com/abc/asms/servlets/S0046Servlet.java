package com.abc.asms.servlets;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.services.S0045Service;
@WebServlet("/S0046.html")
public class S0046Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("user");
		List<String> error = new ArrayList<>();
		S0045Service S45S = new S0045Service();
		HttpSession session = req.getSession();
		if(!S45S.checkDB(mail)) {
			error.add("メールアドレスが存在しません。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
			return;
		}
		req.setAttribute("mail", mail);
		getServletContext().getRequestDispatcher("/WEB-INF/S0046.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> error = validate("", "");
		getServletContext().getRequestDispatcher("/WEB-INF/S0046.jsp").forward(req, resp);
	}

	private List<String> validate(String password,String newPassword) {
		List<String> error = new ArrayList<>();
		if (password == null || password.equals("")) {
			error.add("パスワードを入力して下さい。");
		} else if (30 < password.getBytes(Charset.forName("UTF-8")).length) {
			error.add("パスワードが長すぎます。");
		}
		if (newPassword == null || password.equals("")) {
			error.add("確認用パスワードを入力して下さい。");
		} else if (newPassword.equals(password)) {
			error.add("新パスワードとパスワード（確認）が一致していません。");
		}
		return error;
	}
}
