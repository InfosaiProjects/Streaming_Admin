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
 * Servlet implementation class getLikeCountOnMemberFeed
 */
@WebServlet("/getLikeCountOnMemberFeed")
public class getLikeCountOnMemberFeed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memberId = request.getParameter("memberId");
		String feedId = request.getParameter("feedId");
		System.out.println("in getLikeCountOnMemberFeed");
		
		FeedDao feedDao = null;
		try {
			feedDao = new FeedDao();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			ResultSet rs = feedDao.getLikesAndTotalGifts(memberId, feedId);
			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("success", 1);
				json.put("totalLikes", rs.getObject(1));
				json.put("gift Ids", rs.getObject(2));
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
