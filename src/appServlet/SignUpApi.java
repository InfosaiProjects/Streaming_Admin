package appServlet;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
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
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Prasad, For SignUpApi Table: login
 */
@WebServlet("/SignUpApi")
public class SignUpApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json = new JSONArray();
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in SignUpApi");
		org.json.JSONObject jsonBody = null;
		String emailId = null;
		String username = null;
		String password1 = null;
		String password2 = null;
		String requestBody = request.getReader().lines().collect(Collectors.joining());
		try {
			jsonBody = new org.json.JSONObject(requestBody);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (jsonBody != null) {
			try {
				emailId = (String) jsonBody.get("emailId");
				username = (String) jsonBody.get("username");
				password1 = (String) jsonBody.get("password1");
				password2 = (String) jsonBody.get("password2");
			} catch (Exception x) {
				x.printStackTrace();
			}
		}

		Object[] object = null;
		int status = 0;
		if (password1.equals(password2)) {
			System.out.println("In");
			
			
			AllDao allDao;
			try {
				allDao = new AllDao();
				object = allDao.checkEmailAvialibility(emailId);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Object MemberName = object[0];
			System.out.println("MemberName>>>" + MemberName);
			Object methodStatus = object[1];
			System.out.println("methodStatus>>>" + methodStatus);

			if (methodStatus == null) {
				String memberId = getMemberCode();
				status = AllDao.insertCredentialsForUser(emailId, username, password1, memberId);
			} else {
				System.out.println("Member not inserted");
			}

		} else {
			System.out.println("Member already present Or Password enterd fail");
		}

		if (status == 1) {
			System.out.println(status);
			JSONObject json = new JSONObject();
			json.put("success", 1);
			json1.add(json);
			
			
			String token=generateToken(username,emailId );
			json.put("token",token);

		} else {
			JSONObject json = new JSONObject();
			json.put("success", 0);
			json1.add(json);
		}
		json2.put("data", json1);
		json2.toString();
		System.out.println(json2.toString());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json2.toString());
		json1.clear();
		json2.clear();

	}

	public static String getMemberCode() {

		// create a string of all characters
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 4;

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}
		int number = random.nextInt(999);

		String randomString = sb.toString();
		System.out.println("Random String is: " + randomString);

		return "MEM" + randomString + number;

	}
	
	public  String generateToken(String emailId, String username) {
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
