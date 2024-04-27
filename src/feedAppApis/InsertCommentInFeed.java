package feedAppApis;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.AllDao;
import dao.FeedDao;

/**
 * Servlet implementation class InsertCommentInFeed
 */
@WebServlet("/InsertCommentInFeed")
public class InsertCommentInFeed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in InsertCommentInFeed");
		
		//new code
		org.json.JSONObject jsonBody = null;
		String memberId = null;
		String feedId = null;
		String userId = null;
		String comment = null;
		String requestBody = request.getReader().lines().collect(Collectors.joining());
		try {
			jsonBody = new org.json.JSONObject(requestBody);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (jsonBody != null) {
			try {
				memberId = (String) jsonBody.get("memberId");
				feedId = (String) jsonBody.get("feedId");
				userId = (String) jsonBody.get("userId");
				comment = (String) jsonBody.get("comment");
			} catch (Exception x) {
				x.printStackTrace();
			}
		}

		Object[] object = null;
		int status = 0;
		FeedDao feedDao = null;
		try {
			feedDao = new FeedDao();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

			//existing code
		
		try {
			feedDao = new FeedDao();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*String memberId = request.getParameter("memberId");
		String feedId = request.getParameter("feedId");
		String userId = request.getParameter("userId");
		String comment = request.getParameter("comment");*/
		
		
		status = feedDao.insertCommentFeedAsPerMember(memberId, feedId, userId,comment);

		try {
			if (status == 1) {
				JSONObject json = new JSONObject();
				json.put("success", 1);
				json.put("message", "Comment successfully inserted!!");
				json1.add(json);
			} else {
				JSONObject json = new JSONObject();
				json.put("success", 0);
				json.put("message", "Error! Comment not inserted!!");
				json1.add(json);
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
