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
import com.abc.asms.forms.S0042Form;
import com.abc.asms.services.S0042Service;

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet { //アカウント詳細編集

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//権限チェック→アカウント登録権限がない場合はダッシュボードへ
		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("account");
		int authority = account.getAuthority();
		if(authority != 10 && authority != 11) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//キャンセルで戻ってきた場合

		//S0041からaccount_idを取得
		String accountId = req.getParameter("accountId");
		accountId = "3"; //あとで消す

		//アカウント情報取得
		S0042Form form = new S0042Service().findAccount(accountId);

		//jspへformを渡す
		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0042.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//権限チェック→アカウント登録権限がない場合はダッシュボードへ
		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("account");
		int authority = account.getAuthority();
		if(authority != 10 && authority != 11) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//入力情報取得
		S0042Form form = new S0042Form();
		form.setAccountId(req.getParameter("accountId"));
		form.setName(req.getParameter("name"));
		form.setMail(req.getParameter("mail"));
		form.setInputPass(req.getParameter("inputPass"));
		form.setInputPass2(req.getParameter("inputPass2"));
		form.setSalesAuthority(req.getParameter("salesAuthority"));
		form.setAccountAuthority(req.getParameter("accountAuthority"));

		//バリデーション
		List<String> error = validation(form);

		if(error.isEmpty()) {
			//アカウント編集確認画面へ値を渡して移動
			String input = null;
			resp.sendRedirect("S0043.html?" + input);
		}else {
			//エラー時入力情報formとメッセージを表示、S0042.jspへ
			req.setAttribute("form", form);
			req.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/WEB-INF/S0042.jsp").forward(req, resp);
		}

	}

	public List<String> validation(S0042Form form){

		List<String> error = new ArrayList<>();

		try {

			//氏名必須入力、長さ(バイト数)
			if(form.getName() == null || form.getName().isEmpty()) {
				error.add("氏名を入力して下さい。");
			}else if(21 <= form.getName().getBytes("UTF-8").length) {
				error.add("氏名が長すぎます。");
			}

			//メールアドレス必須入力、長さ(バイト数)、形式
			if(form.getMail() == null || form.getMail().isEmpty()) {
				error.add("メールアドレスを入力して下さい。");
			}else if(101 <= form.getMail().getBytes("UTF-8").length) {
				error.add("メールアドレスが長すぎます。");
			}else if(!form.getMail().matches("^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9._-]*\\\\.[a-zA-Z0-9._-]*$")) {
				error.add("メールアドレスの形式が誤っています。");
			}

			//パスワード長さ(バイト数)、一致
			if(form.getInputPass() != null) {
				if(31 <= form.getInputPass().getBytes("UTF-8").length) {
					error.add("パスワードが長すぎます。");
				}else if(!form.getInputPass().equals(form.getInputPass2())) {
					error.add("パスワードとパスワード（確認）が一致していません。");
				}
			}


		} catch (Exception e) {
			error.add("エラーが発生しました。");
		}


		return null;
	}
}
