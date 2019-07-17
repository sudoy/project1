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
		S0040Service S40S = new S0040Service();
		String name = (String) req.getParameter("name");
		String mail = (String) req.getParameter("mail");
		String salesAuthority = (String) req.getParameter("salesAuthority");
		String accountAuthority = (String) req.getParameter("accountAuthority");
		List<String> error = new ArrayList<String>();
		AccountConditionalForm acf = new AccountConditionalForm(name, mail, accountAuthority, salesAuthority);
		// 異常チェック
		if(abnormalCheck(name, mail, salesAuthority, accountAuthority)) {
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}
		
		error = validate(name, mail, salesAuthority, accountAuthority);
		
		// 入力チェック
		if (error.size() == 0) {
			// エラーなし
			// データ一致件数チェック
			if (0 < S40S.checkListSize(acf)) {
				// データ一致あり
				session.setAttribute("AccountConditional", acf);
				resp.sendRedirect("S0041.html");
				return;
			}
			error.add("検索結果はありません。");
		}
		req.setAttribute("acf", acf);
		session.setAttribute("error", error);
		getServletContext().getRequestDispatcher("/WEB-INF/S0040.jsp").forward(req, resp);
	}

	/**
	 * 異常チェックを行うメソッド
	 * @param name チェック対象
	 * @param mail チェック対象
	 * @param salesAuthority チェック対象
	 * @param accountAuthority チェック対象
	 * @return 真偽値<br>
	 * 入力全て正常ならfalse、異常ならばtrue
	 */
	public boolean abnormalCheck(String name, String mail, String salesAuthority, String accountAuthority) {
		boolean abnormal = false;
		if (name == null || mail == null || salesAuthority == null || accountAuthority == null) {
			abnormal = true;
		} else if(!salesAuthority.matches("(all|no|yes)")||!accountAuthority.matches("(all|no|yes)")){
			abnormal = true;
		}
		return abnormal;
	}

	/**
	 * 入力チェックメソッド
	 * @param name 入力されたname
	 * @param mail 入力されたmail
	 * @param salesAuthority 入力されたsakesAuthority
	 * @param accountAuthority 入力されたaccountAuthority
	 * @return エラーメッセージ
	 */
	public List<String> validate(String name, String mail, String salesAuthority, String accountAuthority) {
		List<String> error = new ArrayList<>();
		if (20 < name.getBytes(Charset.forName("UTF-8")).length) {
			error.add("氏名の指定が長すぎます。");
		}
		if (100 < mail.getBytes(Charset.forName("UTF-8")).length) {
			error.add("メールアドレスの指定が長すぎます。");
		}
		if (!mail.equals("")&&!mail.matches("^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9._-]*\\.[a-zA-Z0-9._-]*$")) {
			error.add("メールアドレスの形式が誤っています。");
		}
		return error;
	}
}
