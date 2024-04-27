package appServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.FeedDao;

@WebServlet("/getFeedById")
public class getFeedById extends HttpServlet{
	
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();
	//String feedId = "";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in getFeedById");
		org.json.JSONObject jsonBody = null;
		String requestBody = request.getReader().lines().collect(Collectors.joining());
		Integer feedId = null;
		try {
			jsonBody = new org.json.JSONObject(requestBody);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (jsonBody != null) {
			try {
				feedId = (Integer) jsonBody.get("id");
			} catch (Exception x) {
				x.printStackTrace();
			}
		}
		FeedDao feedDao = null;
		try {
			feedDao = new FeedDao();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		ResultSet rs;
		try {
			if(feedId!=null) {
			rs = feedDao.getFeedsAllMappedData(feedId);
			try {
				while (rs.next()) {
					JSONObject json = new JSONObject();
					json.put("success", 1);
					json.put("feedId", rs.getObject(1));
					json.put("image", rs.getString(2));
					json.put("dateinserted", rs.getObject(3));
					json.put("about", rs.getObject(4));
					json.put("memberName", rs.getObject(5));
					json.put("memberId", rs.getObject(6));
					json.put("comments", rs.getObject(7));
					json.put("totalDiamonds", rs.getObject(8));
					json.put("totalComments", rs.getObject(9));
					json.put("totalLike", rs.getObject(10));
					json.put("like", rs.getObject(11));
					json1.add(json);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
