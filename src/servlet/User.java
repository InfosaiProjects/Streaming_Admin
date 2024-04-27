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

@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Im in User");
		String task = request.getParameter("task");
		switch (task) {
		case "add": {
			Object[] object = null;
			System.out.println("Im in User add");
			String username = request.getParameter("usernameAdd");
			String password = request.getParameter("passwordAdd");
			String emailId = request.getParameter("emailIdAdd");
			AllDao allDao;
			try {
				allDao = new AllDao();
				object = allDao.checkEmailAvialibility(emailId);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Object MemberName = object[0];
			System.out.println("MemberName>>>" + MemberName);
			Object methodStatus = object[1];
			System.out.println("methodStatus>>>" + methodStatus);

			if (methodStatus == null) {
				String memberId = SignUpApi.getMemberCode();
				int status = AllDao.insertCredentialsForUser(emailId, username, password, memberId);
			} else {
				System.out.println("Member not inserted");
			}

			break;
		}
		case "delete": {
			System.out.println("Im in User delete");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id>>" + id);
			int stat = UserDao.deleteUser(id);
			System.out.println("deletestatus>>>" + stat);
			break;
		}
		case "update": {
			PrintWriter out = response.getWriter();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String emailId = request.getParameter("emailId");
			String id = request.getParameter("id");
			int status = UserDao.updateUser(username, password, emailId, id);
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

		case "updateSuperAdmin": {
			PrintWriter out = response.getWriter();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String id = request.getParameter("id");
			int status = UserDao.updateSuperAdmin(username, password, id);
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
