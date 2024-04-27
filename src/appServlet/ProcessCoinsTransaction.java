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
 * Servlet implementation class ProcessCoinsTransaction
 */
@WebServlet("/ProcessCoinsTransaction")
public class ProcessCoinsTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in ProcessCoinsTransaction");
		CoinsDao coinsDao = null;
		try {
			coinsDao = new CoinsDao();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String success = request.getParameter("success"); // if success is 1 then its true/paid otherwise its
															// false/paymentFail.
		String paymentId = request.getParameter("paymentId");
		String coins = request.getParameter("coins");
		String rs = request.getParameter("rs");
		String memberId = request.getParameter("memberId");
		int status = 0;
		if (success.equals("1") && !paymentId.isEmpty()) {
			status = coinsDao.insertCoinsAsPerMemberId(paymentId, coins, rs, memberId);
		}

		try {
			if (status == 1) {
				JSONObject json = new JSONObject();
				json.put("success", 1);
				json1.add(json);
			} else {
				JSONObject json = new JSONObject();
				json.put("success", 0);
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
