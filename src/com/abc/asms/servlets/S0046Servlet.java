package com.abc.asms.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.S0046Form;
import com.abc.asms.services.S0045Service;
import com.abc.asms.services.S0046Service;
@WebServlet("/S0046.html")
public class S0046Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("user");
		List<String> error = new ArrayList<>();
		S0045Service S45S = new S0045Service();
		HttpSession session = req.getSession();

		// メールアドレスチェック
		if(!S45S.checkDB(mail)) {
			error.add("メールアドレスが存在しません。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
			return;
		}
		mail = URLEncoder.encode(mail , "UTF-8");
		S0046Form S46F = new S0046Form(mail, "", "");
		req.setAttribute("form", S46F);
		getServletContext().getRequestDispatcher("/WEB-INF/S0046.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("user");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		List<String> error = new ArrayList<>();
		HttpSession session = req.getSession();

		// メールアドレスチェック
		S0045Service S45S = new S0045Service();
		if(!S45S.checkDB(mail)) {
			error.add("メールアドレスが存在しません。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
			return;
		}

		S0046Form S46F = new S0046Form(mail, password1, password2);

		// バリデーションチェック
		error = validate(password1, password2);
		if(error.size()==0) {
			S0046Service S46S = new S0046Service();
			// SQLの更新
			S46S.updateDB(S46F);
			List<String> success = new ArrayList<String>();
			success.add("パスワード再設定しました");
			session.setAttribute("success", success);
			resp.sendRedirect("C0010.html");
			return;
		}
		// URLにエンコード
		S46F.setMail(mail = URLEncoder.encode(mail , "UTF-8"));
		session.setAttribute("error", error);
		req.setAttribute("form", S46F);
		getServletContext().getRequestDispatcher("/WEB-INF/S0046.jsp").forward(req, resp);
	}

	/**
	 * 入力チェックメソッド
	 * @param password1 入力されたpassword
	 * @param password2 入力された確認password
	 * @return List(String)型
	 * 入力に問題があればエラーメッセージがaddされる。
	 */
	private List<String> validate(String password1,String password2) {
		List<String> error = new ArrayList<>();
		if (password1 == null || password1.equals("")) {
			error.add("パスワードを入力して下さい。");
		} else if (30 < password1.getBytes(Charset.forName("UTF-8")).length) {
			error.add("パスワードが長すぎます。");
		}
		if (password2 == null || password1.equals("")) {
			error.add("確認用パスワードを入力して下さい。");
		}
		if (error.size() == 0 && !password2.equals(password1)) {
			error.add("新パスワードとパスワード（確認）が一致していません。");
		}
		return error;
	}
}
