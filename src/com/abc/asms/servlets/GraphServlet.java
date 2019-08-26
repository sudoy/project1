package com.abc.asms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.asms.forms.GraphForm;
import com.abc.asms.services.GraphService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/graph")
public class GraphServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int year = Integer.valueOf(req.getParameter("date"));

		GraphForm form = new GraphForm();
		form = new GraphService().getGraphData(year, form);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("thisYear", year);
		map.put("thisYearList", form.getThisYearList());
		map.put("lastYear", (year - 1));
		map.put("lastYearList", form.getLastYearList());
		map.put("maxSale", form.getMaxSale());

		//変換
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(map);
		PrintWriter out = resp.getWriter();
		out.print(jsonStr);
		out.close();
	}

}
