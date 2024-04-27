package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appServlet.ProfileApi;
import dao.CoinsDao;
import dao.ProfileDao;

@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Im in Profile");
		String task = request.getParameter("task");
		switch (task) {
		case "add": {

			System.out.println("Im in Profile add");
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
			System.out.println("Im in Profile delete");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id>>"+id);
			int stat = ProfileDao.deleteProfile(id);
			System.out.println("deletestatus>>>" + stat);
			break;
		}
		case "update": {
			PrintWriter out = response.getWriter();
			String proPic = request.getParameter("proPic");
			String name = request.getParameter("name");
			String country = request.getParameter("country");
			String desc = request.getParameter("desc");
			String id = request.getParameter("id");
			int status = ProfileDao.updateProfile(proPic, name,country,desc,id);
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
		
		case "transferToUser": {
			PrintWriter out = response.getWriter();
			String userId = request.getParameter("userId");
			String userCode = request.getParameter("userCode");
			String range = request.getParameter("range");
			int status = ProfileDao.insertCoinsInUsersAccount(userId,userCode,range);
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
		
		case "block": {
			System.out.println("block");
			int id = Integer.parseInt(request.getParameter("id"));
			String blockTime = request.getParameter("blockTime");
			System.out.println("blockTime>>>" + blockTime);
			System.out.println("id>>"+id);
			int stat = ProfileDao.blockUserWithTime(id,blockTime);
			System.out.println("stat>>>" + stat);
			break;
		}
		
		case "unBlock": {
			System.out.println("unBlock");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id>>"+id);
			int stat = ProfileDao.unBlockUser(id);
			System.out.println("stat>>>" + stat);
			break;
		}
		
		
		}
	}
}
