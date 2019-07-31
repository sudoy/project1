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

import com.abc.asms.forms.SaleConditionalForm;
import com.abc.asms.services.S0021Service;

@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		S0021Service S21S = new S0021Service();

		// 検索条件のセッションが存在しているか
		if (session.getAttribute("SaleConditional") == null) {
			SaleConditionalForm scf = new SaleConditionalForm(new String[]{"",""}, "", null, "", "");
			session.setAttribute("SaleConditional", scf);
		}

		SaleConditionalForm scf = (SaleConditionalForm) session.getAttribute("SaleConditional");

		//ソートの情報を取得
		//2回目以降
		String item = scf.getItem();
		String sort = scf.getSort();
		int page = 0;

		//検索から表示に遷移したとき（1回目）
		if(req.getParameter("page") == null) {
			page = 1;
		}else {
			page = Integer.parseInt(req.getParameter("page"));
		}

		//formに代入
		scf.setItem(item);
		scf.setSort(sort);
		scf.setPage(page);

		//検索結果件数を取得
		List<Integer> pageList = new ArrayList<>();
		pageList = S21S.getResult(scf);
		scf.setPageList(pageList);

		//jspに送信
		req.setAttribute("list", S21S.getDB(scf));
		req.setAttribute("form", scf);
		getServletContext().getRequestDispatcher("/WEB-INF/S0021.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		S0021Service S21S = new S0021Service();

		// 検索条件のセッションが存在しているか
		if (session.getAttribute("SaleConditional") == null) {
			SaleConditionalForm scf = new SaleConditionalForm(new String[]{"",""}, "", null, "", "");
			session.setAttribute("SaleConditional", scf);
		}

		SaleConditionalForm scf = (SaleConditionalForm) session.getAttribute("SaleConditional");
		String item = "";
		String sort = "";
		int page = 0;

		if(req.getParameter("button").equals("sort")) {

			//ソートボタンが押された時
			//ソートの情報を取得
			item = (String) req.getParameter("item");
			sort = (String) req.getParameter("sort");

			//ソートを実行したときは1ページ目から
			page = 1;

			//formに代入
			scf.setItem(item);
			scf.setSort(sort);
			scf.setPage(page);

			req.setAttribute("list", S21S.getDB(scf));
			req.setAttribute("form", scf);
			session.setAttribute("SaleConditional", scf);

			getServletContext().getRequestDispatcher("/WEB-INF/S0021.jsp").forward(req, resp);

		}else {
			//ページ移動ボタンが押された時
			//ページとソートの情報を取得
			String pagecheck = req.getParameter("page");
			item = scf.getItem();
			sort = scf.getSort();
			List<String> error = new ArrayList<>();
			error = validation(pagecheck,scf);

			//エラーメッセージがない場合
			if(error.size() == 0){
				page = Integer.parseInt(req.getParameter("page"));

				//formに代入
				scf.setItem(item);
				scf.setSort(sort);
				scf.setPage(page);

				req.setAttribute("list", S21S.getDB(scf));
				req.setAttribute("form", scf);
				session.setAttribute("SaleConditional", scf);

				getServletContext().getRequestDispatcher("/WEB-INF/S0021.jsp").forward(req, resp);

			//エラーメッセージがある場合
			}else {

				session.setAttribute("error", error);
				req.setAttribute("list", S21S.getDB(scf));
				req.setAttribute("form", scf);
				session.setAttribute("SaleConditional", scf);
				getServletContext().getRequestDispatcher("/WEB-INF/S0021.jsp").forward(req, resp);
			}
		}

	}

	//nullチェックとvalidationメソッド
	public List<String> validation(String pagecheck,SaleConditionalForm scf) throws ServletException{
		S0021Service S21S = new S0021Service();
		List<String> error = new ArrayList<>();
		List<Integer> pageList = new ArrayList<>();
		pageList = S21S.getResult(scf);


		//pageのnullチェック
		if(pagecheck == null || pagecheck.equals("")) {
			error.add("正の整数を入力して下さい。");
			return error;
		}

		//数字以外のものをチェック
		if(!pagecheck.matches("^[0-9]*$")){
			error.add("正の整数を入力して下さい。");
			return error;
		}

		//nullではないことを確認してからキャスト
		int page = Integer.parseInt(pagecheck);

		//1未満の数字をチェック
		if(page < 1) {
			error.add("正の整数を入力して下さい。");
			return error;
		}

		//検索結果より多いページ数をチェック
		if(pageList.size() < page) {
			error.add("検索結果はありません。");
			return error;
		}

		return error;



	}
}
