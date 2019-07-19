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
import com.abc.asms.forms.EntryAccountForm;
import com.abc.asms.services.S0031Service;

@WebServlet("/S0031.html")
public class S0031Servlet extends HttpServlet {

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

		//セッションから登録画面で入力した値を取得
		EntryAccountForm form = (EntryAccountForm) session.getAttribute("EntryAccountForm");

		//formの中身がない場合ダッシュボードへ
		if(form == null) {
			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//jspへ
		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0031.jsp").forward(req, resp);
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

		//form取得、セッションをリセット
		EntryAccountForm form = (EntryAccountForm) session.getAttribute("EntryAccountForm");
		session.setAttribute("EntryAccountForm", null);

		//登録処理
		int accountId = new S0031Service().insert(form);

		//成功メッセージ
		List<String> success = new ArrayList<>();
		success.add("No"+ accountId +"のアカウントを登録しました。");
		session.setAttribute("success", success);

		//アカウント登録へ戻る
		resp.sendRedirect("S0030.html");
	}

}
