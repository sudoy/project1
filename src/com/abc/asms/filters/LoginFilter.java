package com.abc.asms.filters;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.AccountForm;

@WebFilter({"/C0010.html","/C0020.html","/S0010.html","/S0011.html",
	"/S0020.html","/S0021.html","/S0022.html","/S0023.html","/S0024.html","/S0025.html",
	"/S0030.html","/S0031.html",
	"/S0040.html","/S0041.html","/S0042.html","/S0043.html","/S0044.html"})
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 飛んできたPathの取得
		String target = ((HttpServletRequest) req).getServletPath();
		HttpSession session = ((HttpServletRequest) req).getSession();
		AccountForm account = (AccountForm) session.getAttribute("account");

		// ログイン状態とpathの関係チェック
		if(account!=null&&target.equals("/C0010.html")) {
			// ログイン済みでC0010にアクセス
			((HttpServletResponse)resp).sendRedirect("C0020.html");
			return;
		}
		if(account==null&&!target.equals("/C0010.html")){
			// 未ログインでC0010以外にアクセス
			List<String> error = new ArrayList<>();
			error.add("ログインして下さい。");
			session.setAttribute("error", error);
			((HttpServletResponse)resp).sendRedirect("C0010.html");
			return;
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
