package appServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.AllDao;
import dao.ProfileDao;

/**
 * Prasad, For login Table: Profile
 */
@WebServlet("/ProfileApi")
public class ProfileApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json = new JSONArray();
	JSONArray jsonArray = new JSONArray();
	JSONObject json2 = new JSONObject();
	String memberId="";
	Object object[];
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In loginApi");
		 memberId = request.getParameter("memberId");
		System.out.println("memberId>>>" + memberId);
		
		int message = 0;
		String userId = "";
		JSONObject json = new JSONObject();
		ProfileDao profileDao = null;
		try {
			profileDao = new ProfileDao();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		object = profileDao.getProfileDetails(memberId);

		if (object[0] != null) {
			json.put("success", 1);
			json.put("name", object[1]);
			json.put("country", object[3]);
			json.put("description", object[4]);
			json.put("diamonds", object[5]);
			json.put("followers", object[6]);
			json.put("following", object[7]);

		} else {
			json.put("success", 0);
			json.put("name", 0);
			json.put("country", 0);
			json.put("description", 0);
			json.put("diamonds", 0);
			json.put("followers", 0);
			json.put("following", 0);
		}

		json2.put("data", json);
		System.out.println(json2.toString());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json2.toString());
		json.clear();
		json2.clear();

	}

}
