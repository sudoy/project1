package com.abc.asms.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.forms.AccountForm;
import com.abc.asms.forms.S0010Form;
import com.abc.asms.services.S0010Service;
import com.abc.asms.utils.DBUtils;

@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		AccountForm account = (AccountForm) session.getAttribute("account");

		int salesAuthority = account.getAuthority();
		if(salesAuthority != 1 && salesAuthority != 11) {

			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}


		//登録画面に必要な値を取得
		LocalDate date = LocalDate.now();
		String saleDate = DBUtils.dateFormat(date.toString());


		//登録画面に必要な値をServiceから取得
		List<S0010Form> categoryList = new ArrayList<>();
		List<S0010Form> accountList = new ArrayList<>();
		S0010Service service = new S0010Service();

		categoryList = service.findCategory();
		accountList = service.findAccount();

		//jspへ送信して表示
		req.setAttribute("saleDate", saleDate);
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("accountList", accountList);

		getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		//jspで入力された値を取得
		String saleDate = (String) req.getParameter("saleDate");
		String accountId = (String) req.getParameter("accountId");
		String categoryId = (String) req.getParameter("categoryId");
		String tradeName = (String) req.getParameter("tradeName");
		String unitPrice = (String) req.getParameter("unitPrice");
		String saleNumber = (String) req.getParameter("saleNumber");
		String note = (String) req.getParameter("note");

		//formに代入してvalidationメソッドへ通す
		S0010Form form = new S0010Form(saleDate,accountId,categoryId,tradeName,unitPrice,saleNumber,note);
		S0010Service service = new S0010Service();
		List<String>  error = new ArrayList<>();

		error = service.validation(form);

		//エラーメッセージがなければページ遷移
		if(error.isEmpty()) {
			session.setAttribute("form", form);
			resp.sendRedirect("S0011.html");

		//エラーがあれば同じページへ

		}else{

			req.setAttribute("saleDate", saleDate);
			req.setAttribute("form", form);
			req.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);
		}

	}

}
