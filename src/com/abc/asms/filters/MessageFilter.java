package com.abc.asms.filters;
import java.io.IOException;
import java.util.Enumeration;

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

		HttpSession session = ((HttpServletRequest) req).getSession();
		if(((HttpServletResponse) resp).getStatus()==200) {

			session.setAttribute("error", null);
			session.setAttribute("success", null);

			//結合試験で利用、sessionを表示
			Enumeration e = session.getAttributeNames();
			while(e.hasMoreElements()) {
				String key = (String)e.nextElement();
				System.out.println( key + "：" + session.getAttribute(key) + "<br>");
			}
		}



	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
