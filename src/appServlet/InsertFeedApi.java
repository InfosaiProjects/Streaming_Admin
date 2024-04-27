package appServlet;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import dao.FeedDao;

/**
 * Servlet implementation class InsertFeedApi
 */
@MultipartConfig(location = "", fileSizeThreshold = 1073741824, maxFileSize = 5242880L, maxRequestSize = 26214400L)
@WebServlet("/InsertFeedApi")
public class InsertFeedApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray json1 = new JSONArray();
	JSONObject json2 = new JSONObject();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in InsertFeedApi");
		// new code
		String images = (String) request.getParameter("images");
		String memberId = (String) request.getParameter("memberId");
		String about = (String) request.getParameter("about");
		
		
		

		Part part = request.getPart("images");
		String imagesName = extractImagesName(part);
		String savePath = "E:\\JavaProject\\SteremingProject01\\WebContent\\images" + File.separator + imagesName;
		File fileSaveDir = new File(savePath);

		part.write(savePath + File.separator);
		// new code ends
		FeedDao feedDao = null;
		try {
			feedDao = new FeedDao();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * String images = request.getParameter("images"); String memberId =
		 * request.getParameter("memberId"); String about =
		 * request.getParameter("about");
		 */

		int status = 0;
		status = feedDao.insertFeedAsPerMember(fileSaveDir.getName(), memberId, about);

		try {
			if (status == 1) {
				JSONObject json = new JSONObject();
				json.put("success", 1);
				json.put("filePath",fileSaveDir.getAbsolutePath());
				json.put("name",fileSaveDir.getName());

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

	private String extractImagesName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}

		return null;
	}
}
