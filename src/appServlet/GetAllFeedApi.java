package appServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.FeedDao;

/**
 * Servlet implementation class GetAllFeedApi
 */
@WebServlet("/GetAllFeedApi")
public class GetAllFeedApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();
	String memberId = "";
	Object feedId = "";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	System.out.println("in GetAllFeedApi");
		
		// Read query parameters for pagination
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("size"));
		int startIndex = (pageNumber - 1) * pageSize;
		int totalCount = 0;

		FeedDao feedDao = null;
		try {
			feedDao = new FeedDao();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// Get total count of rows
		try {
			totalCount = feedDao.getFeedCount();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Get paginated results
		ResultSet rs;
		try {
			rs = feedDao.getPaginatedFeed(startIndex, pageSize);
			try {
				while (rs.next()) {
					JSONObject json = new JSONObject();
					feedId = rs.getObject(1);
					memberId = rs.getString(2);
					json.put("success", 1);
					json.put("feedId", rs.getObject(1));
					json.put("memberId", rs.getString(2));
					json.put("memberName", rs.getObject(8));
					json.put("dateinserted", rs.getObject(5));
					json.put("image", rs.getObject(4));
					json.put("about", rs.getObject(3));
					
					json1.add(json);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		json2.put("show", json1);
		json2.put("page", pageNumber);
		json2.put("size", pageSize);
		json2.put("total", totalCount);
		
		System.out.println(json2.toString());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json2.toString());
		
		json1.clear();
		json2.clear();
	
	}

}
