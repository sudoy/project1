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
import com.abc.asms.services.S0044Service;

@WebServlet("/S0044.html")
public class S0044Servlet extends HttpServlet {

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

		//id取得
		String accountId = req.getParameter("accountId");

		//form作成
		AccountEditForm form = new S0044Service().findAccount(accountId);

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

		getServletContext().getRequestDispatcher("/WEB-INF/S0044.jsp").forward(req, resp);
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

		//id取得
		String accountId = req.getParameter("accountId");

		//削除処理
		int cnt = new S0044Service().delete(accountId);

		//成功メッセージ
		if(cnt == 1) {
			List<String> success = new ArrayList<>();
			success.add("No"+ accountId +"のアカウントを削除しました。");
			session.setAttribute("success", success);
		}else {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//アカウント検索結果一覧へ戻る
		resp.sendRedirect("S0041.html");
	}
}
