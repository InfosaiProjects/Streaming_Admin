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

import dao.CoinsDao;

/**
 * Servlet implementation class GetAllCoinsApi
 */
@WebServlet("/GetAllCoinsApi")
public class GetAllCoinsApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in GetAllCoinsApi");
		CoinsDao coinsDao = null;
		try {
			coinsDao = new CoinsDao();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = coinsDao.getAllCoins();
		
		try {
			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("success", 1);
				json.put("coins", rs.getObject(2));
				json.put("rs", rs.getObject(3));
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
