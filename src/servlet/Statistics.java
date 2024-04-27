package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AllDao;
import dao.CoinsDao;

/**
 * Servlet implementation class Statistics
 */
@WebServlet("/Statistics")
public class Statistics extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			AllDao allDao = new AllDao();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		System.out.println("Im in Statistics");
		String task = request.getParameter("task");
		switch (task) {
		case "getAllUsersByUser": {
			PrintWriter out = response.getWriter();
			HashMap<String, String> map = new HashMap<String, String>();
			String user = request.getParameter("user");
			map = AllDao.getAllRecorsOfUser(user);
			System.out.println(map);
			out.print(map);
		}
			break;
		case "getAllDataAsPerUser": {
			PrintWriter out = response.getWriter();
			HashMap<String, String> map = new HashMap<String, String>();
			String user = request.getParameter("user");
			String id = request.getParameter("id");
			map = AllDao.getAllDataAsPerUser(user, id);
			out.print(map);
		}
		}

	}

}
