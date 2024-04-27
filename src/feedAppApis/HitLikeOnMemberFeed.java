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
 * Servlet implementation class HitLikeOnMemberFeed
 */
@WebServlet("/HitLikeOnMemberFeed")
public class HitLikeOnMemberFeed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memberId = request.getParameter("memberId");
		String feedId = request.getParameter("feedId");
		String likeOrUnlike = request.getParameter("likeOrUnlike"); // like=1 ans unlike=0
		String userId = request.getParameter("userId");
		Boolean valueData = false;
		FeedDao feedDao = null;
		try {
			feedDao = new FeedDao();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			int status = 0;
			valueData = feedDao.checkTheFeedAvilableInTable(memberId, feedId, likeOrUnlike, userId);
			System.out.println(valueData);
			if (valueData) {
				status = feedDao.updateLikeOnMemberFeed(memberId, feedId, likeOrUnlike, userId);
				System.out.println("asas" + status);
			} else {
				status = feedDao.putLikeOnMemberFeed(memberId, feedId, likeOrUnlike, userId);
				System.out.println(status);
			}

			if (status == 1) {
				JSONObject json = new JSONObject();
				json.put("success", 1);
			}else {
				JSONObject json = new JSONObject();
				json.put("success", 0);
			}

		} catch (Exception e) {
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
