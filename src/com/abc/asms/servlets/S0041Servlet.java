package com.abc.asms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.AccountConditionalForm;
import com.abc.asms.services.S0041Service;

@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		S0041Service S41S = new S0041Service();
		HttpSession session = req.getSession();
		if (session.getAttribute("AccountConditional") == null) {
			resp.sendRedirect("S0040.html");
		}
		AccountConditionalForm acf = (AccountConditionalForm) session.getAttribute("AccountConditional");
		req.setAttribute("list", S41S.getDB(acf));
		getServletContext().getRequestDispatcher("/WEB-INF/S0041.jsp").forward(req, resp);
	}
}
