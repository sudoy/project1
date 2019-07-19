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

import com.abc.asms.forms.AccountForm;
import com.abc.asms.forms.EntryAccountForm;
import com.abc.asms.utils.DBUtils;

@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {

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

		//キャンセルで確認画面から戻ってきたとき
		if(req.getParameter("cancel") != null) {

			//前回入力した値をセッションから取得
			EntryAccountForm form = (EntryAccountForm) session.getAttribute("EntryAccountForm");

			//formで渡す
			req.setAttribute("form", form);

			//セッションをリセット
			session.setAttribute("EntryAccountForm", null);
		}

		//jspへ
		getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp").forward(req, resp);
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

		//入力値を取得
		EntryAccountForm form = new EntryAccountForm();
		form.setName(req.getParameter("name"));
		form.setMail(req.getParameter("mail"));
		form.setPassword1(req.getParameter("password1"));
		form.setPassword2(req.getParameter("password2"));
		form.setSalesAuthority(req.getParameter("salesAuthority"));
		form.setAccountAuthority(req.getParameter("accountAuthority"));

		//バリデーション
		List<String> error = validation(form);

		if(error.isEmpty()) {
			//エラーがなければセッションへ保存して確認画面へ
			session.setAttribute("EntryAccountForm", form);
			resp.sendRedirect("S0031.html");
		}else {
			//エラー時はformとメッセージを渡してjspへ
			req.setAttribute("form", form);
			req.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp").forward(req, resp);
		}
	}

	//バリデーション
	private List<String> validation(EntryAccountForm form) {

		List<String> error = new ArrayList<>();

		//氏名必須入力、長さ(バイト数)
		if(form.getName() == null || form.getName().isEmpty()) {
			error.add("氏名を入力して下さい。");
		}else if(21 <= form.getName().getBytes(Charset.forName("UTF-8")).length) {
			error.add("氏名が長すぎます。");
		}

		//メールアドレス必須入力、長さ(バイト数)、形式、重複
		if(form.getMail() == null || form.getMail().isEmpty()) {
			error.add("メールアドレスを入力して下さい。");
		}else if(101 <= form.getMail().getBytes(Charset.forName("UTF-8")).length) {
			error.add("メールアドレスが長すぎます。");
		}else if(!form.getMail().matches("^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9._-]*\\.[a-zA-Z0-9._-]*$")) {
			error.add("メールアドレスの形式が誤っています。");
		}else if(DBUtils.checkMailDB(form.getMail())) {
			error.add("メールアドレスが既に登録されています。");
		}

		//パスワード必須入力、長さ(バイト数)
		if(form.getPassword1() == null || form.getPassword1().isEmpty()) {
			error.add("パスワードを入力して下さい。");
		}else if(31 <= form.getPassword1().getBytes(Charset.forName("UTF-8")).length) {
			error.add("パスワードが長すぎます。");
		}

		//パスワード（確認）必須入力、等値チェック
		if(form.getPassword2() == null || form.getPassword2().isEmpty()) {
			error.add("パスワード（確認）を入力して下さい。");
		}else if((form.getPassword1() != null && !form.getPassword1().isEmpty()) && !form.getPassword1().equals(form.getPassword2())) {
			error.add("パスワードとパスワード（確認）が一致していません。");
		}

		//売上登録権限必須入力、値チェック
		if(form.getSalesAuthority() == null || form.getSalesAuthority().isEmpty()) {
			error.add("売上登録権限を入力して下さい。");
		}else if(!form.getSalesAuthority().equals("yes") && !form.getSalesAuthority().equals("no")) {
			error.add("売上登録権限に正しい値を入力して下さい。");
		}

		//アカウント登録権限必須入力、値チェック
		if(form.getAccountAuthority() == null || form.getAccountAuthority().isEmpty()) {
			error.add("アカウント登録権限を入力して下さい。");
		}else if(!form.getAccountAuthority().equals("yes") && !form.getAccountAuthority().equals("no")) {
			error.add("アカウント登録権限に正しい値を入力して下さい。");
		}

		return error;
	}
}
