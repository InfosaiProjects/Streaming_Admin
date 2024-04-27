package servlet;

import bean.LoginPojo;
import dao.LoginDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({ "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao logindao;

	public void init() {
		try {
			logindao = new LoginDao();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("exampleInputEmail");
		String password = request.getParameter("exampleInputPassword");

		LoginPojo loginPojo = new LoginPojo();
		loginPojo.setUsername(username);
		loginPojo.setPassword(password);
		try {

			Object object[];
			Object objectSubAdmin[];
			Object objectSuperAdmin[];

			object = logindao.validate(loginPojo);
			objectSubAdmin = logindao.validateSubAdmin(loginPojo);
			objectSuperAdmin=logindao.validateSuperAdmin(loginPojo);
			
			
			if (object[0].toString().equals("true")) {
				System.out.println("Admin sLogin");
				HttpSession session = request.getSession();
				System.out.println("session>>>>" + session);

				session.setAttribute("userSession", username);
				System.out.println("userSession" + username);

				session.setAttribute("userPassword", password);
				System.out.println("userPassword"+ password);

				response.sendRedirect("welcome.jsp");
			} else if (objectSubAdmin[0].toString().equals("true")) {
				System.out.println("Subadmin Login");
				HttpSession session = request.getSession();
				System.out.println("session>>>>" + session);
				
				session.setAttribute("subadminUsername", username);
				System.out.println("subadminUsername" + username);
				
				session.setAttribute("subadminPassword", password);
				System.out.println("subadminPassword" + password);
				
				response.sendRedirect("subAdminDash.jsp");
			}
			else if (objectSuperAdmin[0].toString().equals("true")) {
				System.out.println("Subadmin Login");
				HttpSession session = request.getSession();
				System.out.println("session>>>>" + session);
				
				session.setAttribute("userSession", username);
				System.out.println("userSession" + username);
				
				session.setAttribute("userPassword", password);
				System.out.println("userPassword" + password);
				
				session.setAttribute("userAdminRight","super");
				
				response.sendRedirect("welcome.jsp");
			}
			else {
				request.setAttribute("errorLogout", "Please Enter Valid Username & Password");
				request.getRequestDispatcher("login.jsp").include(request, response);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean getSession(String subadminUsername,String subadminPassword,HttpSession session) {
		Boolean status=false;
		
		if(subadminPassword.isEmpty() || subadminPassword.isEmpty() || session.toString().isEmpty()) {
			status=false;
		}
		else {
			
			status=true;
		}
		System.out.println(status);
		return status;
	}
	
	
	
	
	
}