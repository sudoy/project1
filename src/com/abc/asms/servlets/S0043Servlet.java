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

import com.abc.asms.forms.AccountEditForm;
import com.abc.asms.forms.AccountForm;
import com.abc.asms.services.S0043Service;

@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//権限チェック→アカウント登録権限がない場合はダッシュボードへ
		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("accounts");
		int authority = account.getAuthority();
		if(authority != 10 && authority != 11) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//S0042から値を取得 ※セッション
		AccountEditForm form = null;
		if(session.getAttribute("AccountEditForm") != null) {
			form = (AccountEditForm) session.getAttribute("AccountEditForm");
		}

		//formの中身がない場合ダッシュボードへ
		if(form == null || form.getAccountId() == null) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//jspへ
		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0043.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//権限チェック→アカウント登録権限がない場合はダッシュボードへ
		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("accounts");
		int authority = account.getAuthority();
		if(authority != 10 && authority != 11) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//form取得
		AccountEditForm form = new AccountEditForm();
		if(session.getAttribute("AccountEditForm") != null) {
			form = (AccountEditForm) session.getAttribute("AccountEditForm");
		}

		//更新処理
		int cnt = new S0043Service().update(form);

		//成功メッセージ
		if(cnt == 1) {
			List<String> success = new ArrayList<>();
			success.add("No"+ form.getAccountId() +"のアカウントを更新しました。");
			session.setAttribute("success", success);
		}else {
			List<String> error = new ArrayList<>();
			error.add("No"+ form.getAccountId() +"の更新に失敗しました。");
			session.setAttribute("error", error);
		}

		//アカウント検索結果一覧へ戻る
		resp.sendRedirect("S0041.html");
	}
}

