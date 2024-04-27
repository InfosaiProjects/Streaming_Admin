package feedAppApis;

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

import dao.FeedDao;

/**
 * Servlet implementation class getMemberFeed
 */
@WebServlet("/getMemberFeed")
public class getMemberFeed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memberId = request.getParameter("memberId");
		System.out.println("in getMemberFeed");
		FeedDao feedDao = null;
		try {
			feedDao = new FeedDao();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			ResultSet rs = feedDao.getFeedByMember(memberId);
			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("success", 1);
				json.put("memberId", rs.getObject(2));
				json.put("memberName", rs.getObject(8));
				json.put("dateinserted", rs.getObject(5));
				json.put("image", rs.getObject(4));
				json.put("about", rs.getObject(3));
				json.put("feedId", rs.getObject(3));
				json1.add(json);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		json2.put("show", json1);
		System.out.println(json2.toString());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json2.toString());
		json1.clear();
		json2.clear();

	}

}
