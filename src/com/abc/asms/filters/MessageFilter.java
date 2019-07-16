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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class MessageFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(req, resp);
		if(((HttpServletResponse) resp).getStatus()==200) {
			HttpSession session = ((HttpServletRequest) req).getSession();
			session.setAttribute("error", null);
			session.setAttribute("success", null);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
