package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import dao.AllDao;

/**
 * Servlet implementation class Banner
 */
@WebServlet("/Banner")
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1073741824, maxFileSize = 5242880L, maxRequestSize = 26214400L)
public class Banner extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String getFileName(Part part) {
		String partHeader = part.getHeader("content-disposition");
		String[] arrayOfString;
		int j = (arrayOfString = part.getHeader("content-disposition").split(";")).length;
		for (int i = 0; i < j; i++) {
			String content = arrayOfString[i];
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String photo = "";
		Part filePart = request.getPart("image");
		int bannerId = Integer.parseInt(request.getParameter("bannerId"));
		String headline = request.getParameter("headline");
		String mainContent = request.getParameter("mainContent");
		System.out.println("in servlet 2" + filePart);
		String path = getServletContext().getRealPath("/") + "images/" + "bannerImg";
		String photoName = "banner";
		String convertedPhotoName = getImageInFilePartFormat(filePart, path, photo, request, response, photoName);
		int status = AllDao.insertBanner(convertedPhotoName, bannerId, headline, mainContent);

		response.sendRedirect("banner.jsp");
	}

	private String getImageInFilePartFormat(Part filePart, String path, String photo, HttpServletRequest request,
			HttpServletResponse response, String photoName) throws IOException {
		System.out.println("path >> " + path);
		Random random = new Random();
		Object no = Integer.valueOf(random.nextInt(9999));
		photo = photoName + no + ".jpg";
		File file = new File(path);
//		file.mkdir();
		String fileName = getFileName(filePart);
		OutputStream out = null;
		InputStream filecontent = null;

		System.out.println("photo>>" + photo);
		PrintWriter writer = response.getWriter();
		out = new FileOutputStream(new File(path + File.separator + photo));
		filecontent = filePart.getInputStream();
		int read = 0;
		byte[] bytes = new byte['?'];
		while ((read = filecontent.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		System.out.println("getImageInFilePartFormat Executed");
		return photo;
	}
}
