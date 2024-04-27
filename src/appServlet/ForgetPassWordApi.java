package appServlet;

import java.io.IOException;
import java.sql.ResultSet;

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
@WebServlet("/ForgetPassWordApi")
public class ForgetPassWordApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In ForgetPassWordApi");
		String emailId = request.getParameter("emailId");
		Object checkStatus[];

		try {
			AllDao allDao = new AllDao();
			checkStatus = allDao.checkEmailAvialibility(emailId);
			JSONObject json = new JSONObject();
			if (checkStatus[1] != null) {

				json.put("success", 1);
				json.put("message", "Email Id Found");
				json.put("status", "true");
				json.put("memberId", checkStatus[2]);
				json.put("memberName", checkStatus[0]);
				json.put("emailId", emailId);

			} else {
				json.put("success", 0);
				json.put("message", "Email Id Not Found");
				json.put("status", "false");
				json.put("memberId", "");
				json.put("memberName", "");
				json.put("emailId", emailId);
			}

			json2.put("data", json);
			System.out.println(json2.toString());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json2.toString());
			json.clear();
			json2.clear();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
