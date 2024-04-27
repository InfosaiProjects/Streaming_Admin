package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appServlet.SignUpApi;
import dao.AllDao;
import dao.UserDao;
import dao.subAdminDao;

/**
 * Servlet implementation class SubAdmin
 */
@WebServlet("/SubAdmin")
public class SubAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Im in SubAdmin");
		String task = request.getParameter("task");
		switch (task) {
		case "add": {
			Object[] object = null;
			System.out.println("Im in SubAdmin add");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String agencies = request.getParameter("agencies");

			String emailId = request.getParameter("emailId");
			String userPermission = request.getParameter("userPermission");
			String totalActiveUsers = request.getParameter("totalActiveUsers");
			String rechargePermission = request.getParameter("rechargePermission");
			String commisionPercentage = request.getParameter("commisionPercentage");
			subAdminDao subAdminDao = null;
			try {
				subAdminDao = new subAdminDao();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			object = subAdminDao.checkEmailAvialibility(emailId, agencies);

			Object nameOfAgencies = object[0];
			System.out.println("nameOfAgencies>>>" + nameOfAgencies);
			Object emailIdSubadmin = object[2];
			System.out.println("emailIdSubadmin>>>" + emailIdSubadmin);

			if (nameOfAgencies == null || emailIdSubadmin == null) {
				int status = subAdminDao.insertSubadminDetails(username, password, agencies, emailId, userPermission,
						totalActiveUsers, rechargePermission, commisionPercentage);
			} else {
				System.out.println("SubAdmin not inserted");
			}

			break;
		}
		case "delete": {
			System.out.println("Im in SubAdmin delete");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id>>" + id);
			int stat = subAdminDao.deleteSubAdmin(id);
			System.out.println("deletestatus>>>" + stat);
			break;
		}
		case "update": {
			System.out.println("Im in SubAdmin Update");
			PrintWriter out = response.getWriter();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String agencies = request.getParameter("agencies");

			String emailId = request.getParameter("emailId");
			String userPermission = request.getParameter("userPermission");
			String totalActiveUsers = request.getParameter("totalActiveUsers");
			String rechargePermission = request.getParameter("rechargePermission");
			String commisionPercentage = request.getParameter("commisionPercentage");

			String id = request.getParameter("id");
			int status = subAdminDao.updateAdminUser(username, password, agencies, emailId, userPermission,
					totalActiveUsers, rechargePermission, commisionPercentage, id);
			if (status == 0) {
				String notInsertedMessage = "Something went worng Not Inserted";
				response.sendRedirect(
						"users_manage.jsp?notInsertedMessage=" + URLEncoder.encode(notInsertedMessage, "UTF-8"));
			} else {
				String message = "Inserted Successfully";
				response.sendRedirect("users_manage.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			}
			out.close();
		}
			break;
		}

	}

}
