package com.abc.asms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class DeleteConditionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//pathとsession、検索条件取得
		String target = ((HttpServletRequest) req).getServletPath();
		HttpSession session = ((HttpServletRequest) req).getSession();

		//検索条件が必要なURL以外にアクセスした時はリセットする
		if(!target.matches("/S002[0-5]\\.html") && session.getAttribute("SaleConditional") != null) {
			session.setAttribute("SaleConditional", null);
		}
		if(!target.matches("/S004[0-4]\\.html") && session.getAttribute("AccountConditional") != null) {
			session.setAttribute("AccountConditional", null);
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
