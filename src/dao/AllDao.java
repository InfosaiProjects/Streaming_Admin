package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AllDao {
	public static Connection con;
	public static Statement statement;
	public static ResultSet rs;
	static PreparedStatement pst;
	static PreparedStatement ps;

	public AllDao() throws ClassNotFoundException {
		try {
			con = ConnectionDao.getConnection();
			System.out.println("Connecting with DataService");
			statement = con.createStatement();

		} catch (SQLException m) {
			System.out.println("==>-" + m);
		}
	}

	public Statement getStatement() {
		return statement;
	}

	public void validateSession() {

	}

	public static ResultSet getLoginDetails(String username, String password) {
		try {
			rs = statement.executeQuery(
					"select * from login where username='" + username + "' && password='" + password + "'");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static int insertCredentialsForUser(String emailId, String username, String password1, String memberId) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into login(username,password,userId,emailId) values (?,?,?,?)");
			ps.setObject(1, username);
			ps.setObject(2, password1);
			ps.setObject(3, memberId);
			ps.setObject(4, emailId);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public Object[] checkEmailAvialibility(String emailId) {
		System.out.println("In checkEmailAvialibility");
		Object[] object = new Object[3];
		try {
			rs = statement.executeQuery("SELECT * FROM login WHERE emailId='" + emailId + "'");

			if (rs.next()) {
				object[0] = rs.getString(2);
				object[1] = 1;
				object[2] = rs.getString(4);
			}
			System.out.println(Arrays.toString(object));

		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return object;
	}

	public int updateCredentialsForUserForForgotPassword(String memberId, String emailId, String password1) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update login set password=? where userId=? && emailId=?");
			ps.setObject(1, password1);
			ps.setObject(2, memberId);
			ps.setObject(3, emailId);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static void InvalidateTheSession(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		System.out.println("In InvalidateTheSession");
		if (session == null || !request.isRequestedSessionIdValid()) {
			// comes here when session is invalid.
			System.out.println("asdasda");
			try {
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static ResultSet getAllLevel() {
		try {
			rs = statement.executeQuery("select * from level");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static int insertLevels(String levelAdd, String coinsAdd) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into level(level,coins) values (?,?)");
			ps.setObject(1, levelAdd);
			ps.setObject(2, coinsAdd);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int deleteLevel(int id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from level where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int updateLevel(String level, String coins, String id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update level set level=?, coins=? where id=?");
			ps.setObject(1, level);
			ps.setObject(2, coins);
			ps.setObject(3, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static ResultSet getAllIssues() {
		try {
			rs = statement.executeQuery("select * from reportIssue");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static int insertIssue(String issueHeadingAdd, String issueDescAdd) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into reportIssue(issueHeading,issueDesc) values (?,?)");
			ps.setObject(1, issueHeadingAdd);
			ps.setObject(2, issueDescAdd);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int deleteIssue(int id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from reportIssue where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int updateIssue(String rHeading, String rDesc, String id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("update reportIssue set issueHeading=?, issueDesc=? where id=?");
			ps.setObject(1, rHeading);
			ps.setObject(2, rDesc);
			ps.setObject(3, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static ResultSet getAllReportedIssues() {
		try {
			rs = statement.executeQuery(
					"SELECT memberReport.*,reportIssue.`issueHeading`,profile.name FROM memberReport INNER JOIN reportIssue ON  memberReport.`issueId`=reportIssue.`id` INNER JOIN streamingApp.profile ON memberReport.memberId=profile.`memberId` ");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static ResultSet getAllOpenReportedIssues() {
		try {
			rs = statement.executeQuery(
					"SELECT memberReport.*,reportIssue.`issueHeading`,profile.name FROM memberReport INNER JOIN reportIssue ON  memberReport.`issueId`=reportIssue.`id` INNER JOIN streamingApp.profile ON memberReport.memberId=profile.`memberId` where memberReport.status='open' ");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static ResultSet getAllCloseReportedIssues() {
		try {
			rs = statement.executeQuery(
					"SELECT memberReport.*,reportIssue.`issueHeading`,profile.name FROM memberReport INNER JOIN reportIssue ON  memberReport.`issueId`=reportIssue.`id` INNER JOIN streamingApp.profile ON memberReport.memberId=profile.`memberId` where memberReport.status='closed' ");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static ResultSet getAllDiamondLevel() {
		try {
			rs = statement.executeQuery("select * from levelDiamonds");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static int insertDiamondsLevels(String levelAdd, String diamondsAdd) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into levelDiamonds(level,diamonds) values (?,?)");
			ps.setObject(1, levelAdd);
			ps.setObject(2, diamondsAdd);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int insertBanner(String convertedPhotoName, int bannerId, String headline, String mainContent) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update banner set bannerId=?, imagePath=?,headline=?,mainContent=? where id=?");
			ps.setObject(1, bannerId);
			ps.setObject(2, convertedPhotoName);
			ps.setObject(3, headline);
			ps.setObject(4, mainContent);
			ps.setObject(5, bannerId);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static ResultSet getFirstImage(int no) {
		try {
			rs = statement.executeQuery("select * from banner where bannerId=" + no);
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static HashMap<String, String> getAllRecorsOfUser(String user) {
		HashMap< String, String> map = new HashMap<String, String>();
		if(user.equals("agency")) {
			
			try {
				rs = statement.executeQuery("SELECT id,nameOfAgencies FROM subAdmin");

				while (rs.next()) {
					map.put(rs.getString(1),rs.getString(2));
				}
				System.out.println(map);
			} catch (SQLException m) {
				System.out.println("==>" + m);
			}
			
		}if(user.equals("user")) {
			try {
				rs = statement.executeQuery("SELECT memberId,name FROM profile");

				while (rs.next()) {
					map.put(rs.getString(1),rs.getString(2));
				}
				System.out.println(map);
			} catch (SQLException m) {
				System.out.println("==>" + m);
			}
		}
		
		return map;
	}

	public static HashMap<String, String> getAllDataAsPerUser(String user, String id) {
		HashMap< String, String> map = new HashMap<String, String>();
		if(user.equals("agency")) {
			try {
				rs = statement.executeQuery("SELECT id,nameOfAgencies FROM subAdmin where id='"+id+"'");

				while (rs.next()) {
					map.put(rs.getString(1),rs.getString(2));
				}
				System.out.println(map);
			} catch (SQLException m) {
				System.out.println("==>" + m);
			}
			
		}if(user.equals("user")) {
			try {
				rs = statement.executeQuery("SELECT memberId,name FROM profile where memberId='"+id+"'");

				while (rs.next()) {
					map.put(rs.getString(1),rs.getString(2));
				}
				System.out.println(map);
			} catch (SQLException m) {
				System.out.println("==>" + m);
			}
		}
		
		return map;
	}

}