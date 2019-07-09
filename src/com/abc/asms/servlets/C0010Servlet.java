package com.abc.asms.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/C0010.html")
public class C0010Servlet extends HttpServlet {
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	getServletContext().getRequestDispatcher("/WEB-INF/C0010.jsp").forward(req, resp);
	}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		List<String> error = validate(mail, password);
		if(0<error.size()) {
			req.setAttribute("error",error);
			getServletContext().getRequestDispatcher("/WEB-INF/C0010.jsp").forward(req, resp);
			return;
		}
	}
	private List<String> validate(String mail,String password) {
		List<String> error = new ArrayList<>();
		if(mail==null||mail.equals("")) {
			error.add("メールアドレスを入力して下さい。");
		}else if(100<mail.length()){
			error.add("メールアドレスが長すぎます。");
		}else if(!mail.matches("^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9._-]*\\.[a-zA-Z0-9._-]*$")){
			error.add("メールアドレスを正しく入力して下さい。");
		}
		if(password==null||password.equals("")) {
			error.add("パスワードが未入力です。");
		}else if(30<password.length()){
			error.add("パスワードが長すぎます。");
		}
		return error;
	}
}