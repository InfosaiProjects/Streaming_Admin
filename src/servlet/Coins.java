package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CoinsDao;
import dao.UserDao;

@WebServlet("/Coins")
public class Coins extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Im in Coins");
		String task = request.getParameter("task");
		switch (task) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			CoinsDao coinsDao = new CoinsDao();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Im in Coins");

		String task = request.getParameter("task");
		switch (task) {
		case "add": {

			System.out.println("Im in Coins add");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			String coinsAdd = request.getParameter("coinsAdd");
			String rsAdd = request.getParameter("rsAdd");

			int status = CoinsDao.insertCoinsPlans(coinsAdd, rsAdd);
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
		case "delete": {
			System.out.println("Im in Coins delete");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id>>" + id);
			int stat = CoinsDao.deleteCoins(id);
			System.out.println("deletestatus>>>" + stat);
			break;
		}
		case "update": {
			PrintWriter out = response.getWriter();
			String coins = request.getParameter("coins");
			String rs = request.getParameter("rs");
			String id = request.getParameter("id");
			int status = CoinsDao.updateCoinsMaster(coins, rs, id);
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

		case "transferToAgency": {

			System.out.println("Im in Coins transferToAgency");
			response.setContentType("text/html");

			String agencyCode = request.getParameter("agencyCode");
			String range = request.getParameter("range");

			int status = CoinsDao.insertCoinsInAgencyAccount(agencyCode, range);
			System.out.println(status);

			break;
		}
		case "getTheCoinsByMember": {
			response.setContentType("text/plain");
			System.out.println("Im in Coins getTheCoinsByMember");
			String memberId = request.getParameter("id");
			System.out.println(memberId);
			int coin = CoinsDao.getTheCoinsByMemberId(memberId);
			PrintWriter out=response.getWriter();
			out.print(coin);
			break;
		}

		}

	}

}
