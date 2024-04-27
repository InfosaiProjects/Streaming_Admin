package appServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.AllDao;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
/**
 * Prasad, For login Table: login
 */
@WebServlet("/loginApi")
public class loginApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json = new JSONArray();
	JSONArray jsonArray = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In loginApi");
		org.json.JSONObject jsonBody = null;
		String username = null;
		String password = null;
		String requestBody = request.getReader().lines().collect(Collectors.joining());
		try {
			jsonBody = new org.json.JSONObject(requestBody);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (jsonBody != null) {
			try {
				
				username = (String) jsonBody.get("username");
				password = (String) jsonBody.get("password");
			} catch (Exception x) {
				x.printStackTrace();
			}
		}

		int message = 0;
		String userId = "";
		try {
			JSONObject json = new JSONObject();
			AllDao allDao = new AllDao();
			ResultSet rs = AllDao.getLoginDetails(username, password);
			while (rs.next()) {
				message = 1;
				userId = rs.getString(4);
				System.out.println("userId>>" + userId);
			}

			System.out.println(message);
			System.out.println(userId);
			if (message == 1 && !userId.isEmpty()) {
				System.out.println("in");
				json.put("success", 1);
				json.put("userId", userId);
				
				String token=generateToken(userId);
				json.put("token",token);
			} else {
				json.put("success", 0);
				json.put("userId", 0);
			}

			json2.put("data", json);
			System.out.println(json2.toString());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json2.toString());
			json.clear();
			json2.clear();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public  String generateToken(String userId ) {
	    String secretKey = "infosai"; 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	    byte[] keyBytes = key.getEncoded();

	    String token = Jwts.builder()
	        .setSubject("userId") 
	        .setIssuedAt(now) 
	        .setExpiration(new Date(nowMillis + 86400000)) 
	        .signWith(SignatureAlgorithm.HS256, keyBytes) 
	        .compact();

	    System.out.println(token);
	    return token;
	  }
	     

}
