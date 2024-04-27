package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AllDao;
import dao.CoinsDao;

/**
 * Servlet implementation class Level
 */
@WebServlet("/Level")
public class Level extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Level() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Im in Level");
		String task = request.getParameter("task");
		switch (task) {
		case "add": {

			System.out.println("Im in Level add");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			String levelAdd = request.getParameter("levelAdd");
			String coinsAdd = request.getParameter("coinsAdd");

			int status = AllDao.insertLevels(levelAdd, coinsAdd);
			break;
		}
		case "delete": {
			System.out.println("Im in Level delete");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id>>" + id);
			int stat = AllDao.deleteLevel(id);
			System.out.println("deletestatus>>>" + stat);
			break;
		}
		case "update": {
			PrintWriter out = response.getWriter();
			String level = request.getParameter("level");
			String coins = request.getParameter("coins");
			String id = request.getParameter("id");
			int status = AllDao.updateLevel(level,coins,id);
			if (status == 0) {
				String notInsertedMessage = "Something went worng Not Inserted";
				response.sendRedirect(
						"users_manage.jsp?notInsertedMessage=" + URLEncoder.encode(notInsertedMessage, "UTF-8"));
			} else {
				String message = "Inserted Successfully";
				response.sendRedirect("users_manage.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			}
			out.close();
			break;
		}
		
		case "addDiamondsLevel": {

			System.out.println("Im in Level add");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			String levelAdd = request.getParameter("levelAdd");
			String diamondsAdd = request.getParameter("diamondsAdd");

			int status = AllDao.insertDiamondsLevels(levelAdd, diamondsAdd);
			break;
		}
		
		
		
		

		}

	}

}
