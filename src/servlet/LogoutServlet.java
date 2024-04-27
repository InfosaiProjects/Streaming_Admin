package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logOut")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In logOut");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setAttribute("errorLogout", "You are successfully Log Out");
		request.getRequestDispatcher("login.jsp").include(request, response);

		HttpSession session = request.getSession();
		session.invalidate();
		out.close();System.out.println("session>>"+session);
	}
}