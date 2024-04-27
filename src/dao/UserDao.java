package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
	public static Statement statement;
	public static ResultSet rs;
	static PreparedStatement pst;
	static PreparedStatement ps;
	public static Connection con;

	public UserDao() throws ClassNotFoundException {
		try {
			con = ConnectionDao.getConnection();
			System.out.println("Connecting with DataService");
			statement = con.createStatement();

		} catch (SQLException m) {
			System.out.println("==>-" + m);
		}
	}


	public static int deleteUser(int id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from login where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int updateUser(String username, String password, String emailId, String id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update login set username=?,password=?,emailId=? where id=?");
			ps.setObject(1, username);
			ps.setObject(2, password);
			ps.setObject(3, emailId);
			ps.setObject(4, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int insertUser(String usernameAdd, String passwordAdd, String emailIdAdd) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into login(username,password,emailId) values(?,?,?)");
			ps.setObject(1, usernameAdd);
			ps.setObject(2, passwordAdd);
			ps.setObject(3, emailIdAdd);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}


	public static int updateSuperAdmin(String username, String password, String id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update adminLogin set username=?,password=? where id=? && rights=?");
			ps.setObject(1, username);
			ps.setObject(2, password);
			ps.setObject(3, id);
			ps.setObject(4, "superAdmin");
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}



}
