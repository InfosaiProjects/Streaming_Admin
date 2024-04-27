package appServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.CoinsDao;

/**
 * Coins: table coins
 */
@WebServlet("/GetCoinsApi")
public class GetCoinsApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json = new JSONArray();
	JSONArray jsonArray = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In GetCoinsApi");
		String memberId = request.getParameter("memberId");
		Object object[];
		JSONObject json = new JSONObject();
		object = CoinsDao.getCoinsDetails(memberId);

		if (object[0] == null) {
			json.put("success", 0);
			json.put("noOfCoins", 0);

		} else {
			json.put("success", 1);
			json.put("noOfCoins", object[0]);
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
