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

import dao.CoinsDao;
import dao.FeedDao;

/**
 * Servlet implementation class getFeedComments
 */
@WebServlet("/getFeedComments")
public class getFeedComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memberId = request.getParameter("memberId");
		String feedId = request.getParameter("feedId");
		Boolean valueData = false;
		FeedDao feedDao = null;
		try {
			feedDao = new FeedDao();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		ResultSet rs = feedDao.getCommentsByMemberIdAndFeedId(memberId, feedId);

		try {
			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("success", 1);
				json.put("memberId", rs.getObject(2));
				json.put("feedId", rs.getObject(3));
				json.put("userId", rs.getObject(4));
				json.put("comment", rs.getObject(5));
				json.put("dateTime", rs.getObject(6));
				json.put("userName", rs.getObject(7));
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
