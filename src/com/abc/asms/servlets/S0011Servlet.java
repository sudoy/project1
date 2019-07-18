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
import com.abc.asms.forms.EntrySaleForm;
import com.abc.asms.services.S0011Service;
import com.abc.asms.utils.DBUtils;

@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//session開始
		HttpSession session = req.getSession();

		//権限チェック
		AccountForm account = (AccountForm) session.getAttribute("account");
		int salesAuthority = account.getAuthority();
		if(salesAuthority != 1 && salesAuthority != 11) {

			List<String> error = new ArrayList<>();
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//S0010の入力データを取得
		String saleDate = req.getParameter("saleDate");
		String accountId = req.getParameter("accountId");
		String categoryId = req.getParameter("categoryId");
		String tradeName = req.getParameter("tradeName");
		String unitPrice = req.getParameter("unitPrice");
		String saleNumber = req.getParameter("saleNumber");
		String note = req.getParameter("note");

		//値をformにセット
		EntrySaleForm form = new EntrySaleForm(saleDate,accountId,categoryId,tradeName,unitPrice,saleNumber,note);
		List<String> error = new ArrayList<>();


		//URLなどで不正な値が入力されていないかチェック
		if(isnull(form)) {
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//小計を計算するメソッドの呼び出し
		S0011Service service = new S0011Service();
		String subTotal = service.calc(unitPrice,saleNumber);
		form.setSubTotal(subTotal);


		//選択肢のリストを抽出
		List<EntrySaleForm> accountList = new ArrayList<>();
		List<EntrySaleForm> categoryList = new ArrayList<>();

		accountList = DBUtils.findAccount();
		categoryList = DBUtils.findCategory();

		//キャンセルがあったときに送るデータ
		StringBuilder canceldata = DBUtils.sendData(form);
		form.setCanceldata(canceldata);

		//formをjspへ送信
		req.setAttribute("form", form);
		req.setAttribute("accountList", accountList);
		req.setAttribute("categoryList", categoryList);

		getServletContext().getRequestDispatcher("/WEB-INF/S0011.jsp").forward(req, resp);


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		//S0010で入力された値を取得

		String saleDate = req.getParameter("saleDate");
		String accountId = req.getParameter("accountId");
		String categoryId = req.getParameter("categoryId");
		String tradeName = req.getParameter("tradeName");
		String unitPrice = req.getParameter("unitPrice");
		String saleNumber = req.getParameter("saleNumber");
		String note = req.getParameter("note");

		EntrySaleForm form = new EntrySaleForm(saleDate,accountId,categoryId,tradeName,unitPrice,saleNumber,note);
		List<String> error = new ArrayList<>();


		//URLなどで不正な値が入力されていないかチェック
		if(isnull(form)) {
			error.add("不正なアクセスです。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0020.html");
			return;
		}

		//insert開始
		S0011Service service = new S0011Service();
		int id = service.insert(form);

		//成功メッセージ
		session.setAttribute("success", "No" + id + "の売上を登録しました。");
		resp.sendRedirect("S0010.html");

	}


	//nullチェック
	public boolean isnull(EntrySaleForm form) {

		//エラーがある場合はダッシュボード
		if(form.getSaleDate() == null) {
			return true;
		}

		if(form.getAccountId() == null) {
			return true;
		}

		if(form.getCategoryId() == null) {
			return true;
		}

		if(form.getTradeName() == null) {
			return true;
		}

		if(form.getUnitPrice() == null) {
			return true;
		}

		if(form.getSaleNumber() == null) {
			return true;
		}

		if(form.getNote() == null) {
			return true;

		}

		return false;
	}


}
