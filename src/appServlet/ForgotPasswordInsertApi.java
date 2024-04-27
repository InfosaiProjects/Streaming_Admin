package appServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.AllDao;

/**
 * Prasad, table: login
 */
@WebServlet("/ForgotPasswordInsertApi")
public class ForgotPasswordInsertApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In ForgotPasswordInsertApi");
		String emailId = request.getParameter("emailId");
		String memberId = request.getParameter("memberId");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		int status = 0;
		try {
			AllDao allDao = new AllDao();

			if (password1.equals(password2)) {
				System.out.println("In");
				status = allDao.updateCredentialsForUserForForgotPassword(memberId, emailId, password1);
			}
			if (status == 1) {
				System.out.println(status);
				JSONObject json = new JSONObject();
				json.put("success", 1);
				json.put("message", "Password Changed Successfully!");

				json1.add(json);
			} else {
				JSONObject json = new JSONObject();
				json.put("success", 0);
				json.put("message", "Email Or MemberId is not matched");
				json1.add(json);
			}
			json2.put("data", json1);
			System.out.println(json2.toString());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json2.toString());
			json1.clear();
			json2.clear();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
}
