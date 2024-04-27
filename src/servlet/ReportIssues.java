package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AllDao;

/**
 * Servlet implementation class ReportIssues
 */
@WebServlet("/ReportIssues")
public class ReportIssues extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		System.out.println("Im in ReportIssues");
		String task = request.getParameter("task");
		switch (task) {
		case "add": {

			System.out.println("Im in ReportIssues add");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String issueHeadingAdd = request.getParameter("issueHeadingAdd");
			String issueDescAdd = request.getParameter("issueDescAdd");

			int status = AllDao.insertIssue(issueHeadingAdd, issueDescAdd);
			break;
		}
		case "delete": {
			System.out.println("Im in ReportIssues delete");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id>>" + id);
			int stat = AllDao.deleteIssue(id);
			System.out.println("deletestatus>>>" + stat);
			break;
		}
		case "update": {
			PrintWriter out = response.getWriter();
			String rHeading = request.getParameter("rHeading");
			String rDesc = request.getParameter("rDesc");
			String id = request.getParameter("id");
			int status = AllDao.updateIssue(rHeading,rDesc,id);
			if (status == 0) {
				String notInsertedMessage = "Something went worng Not Inserted";
				response.sendRedirect(
						"users_manage.jsp?notInsertedMessage=" + URLEncoder.encode(notInsertedMessage, "UTF-8"));
			} else {
				String message = "Inserted Successfully";
				response.sendRedirect("users_manage.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			}
			out.close();
			break;
		}

		}

	
	}

}
