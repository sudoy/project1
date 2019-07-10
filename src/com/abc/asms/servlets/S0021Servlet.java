package com.abc.asms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.SaleConditionalForm;
import com.abc.asms.services.S0021Service;

@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession();
	SaleConditionalForm scf = (SaleConditionalForm) session.getAttribute("SaleConditional");
	S0021Service S21S = new S0021Service();
	req.setAttribute("list", S21S.getDB(scf));
	getServletContext().getRequestDispatcher("/WEB-INF/S0021.jsp").forward(req, resp);
	}
}